import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { UserService } from '@src/app/authentication/services/user.service';
import {AuthenticationService} from '@src/app/authentication/services/authentication.service';

@Component({templateUrl: 'registration.component.html'})
export class RegistrationComponent implements OnInit {
    registerForm: FormGroup;
    submitted = false;

    constructor(
        private formBuilder: FormBuilder,
        private router: Router,
        private authenticationService: AuthenticationService,
        private userService: UserService
    ) {
        // se e' gia' connesso fa il redirect sulla home
        if (this.authenticationService.currentUserValue) {
            this.router.navigate(['/']);
        }
    }

    ngOnInit() {
        this.registerForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', [Validators.required, Validators.minLength(10)]]
        });
    }

    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
        if (this.registerForm.invalid) {
            return;
        }

        this.userService.register(this.registerForm.value)
            .subscribe(
                data => {
                    alert('Registrazione effettuata');
                    console.log(data);
                    this.router.navigate(['/']);
                },
                error => {
                    alert('Registrazione non effettuata');
                    console.log(error);
                });
    }
}
