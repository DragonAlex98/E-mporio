import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { AuthenticationService } from '@src/app/authentication/services/authentication.service';
import { NotificationService } from '@src/app/notification.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  submitted = false;
  returnUrl: string; // path dove ritorno dopo il login

  constructor(
      private formBuilder: FormBuilder,
      private route: ActivatedRoute,
      private router: Router,
      private authenticationService: AuthenticationService,
      private notificationService: NotificationService,
  ) {
      // se gia' loggato torno alla home
      if (this.authenticationService.currentUserValue) {
          this.router.navigate(['/']);
      }
  }

  ngOnInit() {
      this.loginForm = this.formBuilder.group({
          username: ['', Validators.required],
          password: ['', Validators.required]
      });

      // memorizzo il path dove sono attualmente per ritornarci dopo il login, se non c'e' vado in '/'
      this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }


  onSubmit() {
      this.submitted = true;

      // la form non e' valida non effettuo la chiamata
      if (this.loginForm.invalid) {
          return;
      }

      this.authenticationService.login(this.loginForm.controls.username.value, this.loginForm.controls.password.value)
          .subscribe(
              data => {
                  this.notificationService.success('Login effettuato con successo!');
                  this.router.navigate([this.returnUrl]);
              }
          );
  }

}
