import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { UserService } from '@src/app/authentication/services/user.service';
import {AuthenticationService} from '@src/app/authentication/services/authentication.service';
import { Role } from '../models/role';
import { NotificationService } from '@src/app/notification.service';

@Component({templateUrl: 'registration.component.html'})
export class RegistrationComponent implements OnInit {
    registerForm: FormGroup;
    submitted = false;
    role = Role;
    roles = Object.values(this.role).filter(x => x != Role.Admin && x != Role.OperatoreSistema); // array per la visualizzazione dell'enum dei ruoli

    constructor(
        private formBuilder: FormBuilder,
        private router: Router,
        private authenticationService: AuthenticationService,
        private userService: UserService,
        private notificationService: NotificationService,
    ) {
        // se e' gia' connesso fa il redirect sulla home
        if (this.authenticationService.currentUserValue) {
            this.router.navigate(['/']);
        }
    }

    ngOnInit() {
        this.registerForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', [Validators.required, Validators.minLength(10)]],
            role : ['', [Validators.required]]
        });
    }

    onSubmit() {
        this.submitted = true;

        // se la form non e' valida non effettuo neanche la chiamata
        if (this.registerForm.invalid) {
            return;
        }

        this.userService.register(this.registerForm.value)
            .subscribe(
                data => {
                    this.notificationService.success('Registrazione effettuata');
                    console.log(data);
                    this.router.navigate(['/']);
                }
            );
    }
}
