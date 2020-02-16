import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormGroupDirective, NgForm } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material';
import { AuthenticationService } from '../services/authentication.service';
import { Router } from '@angular/router';
import { NotificationService } from '@src/app/notification.service';

class CrossFieldErrorMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    return control.dirty && form.invalid;
  }
}

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  errorMatcher = new CrossFieldErrorMatcher();

  checkPasswords(changePasswordForm: FormGroup) { 
    const pass = changePasswordForm.get('newPassword');
    const confirmPass = changePasswordForm.get('confirmNewPassword');

    return pass.value === confirmPass.value ? null : { notSame: true }
  }

  changePasswordForm = new FormGroup({
    oldPassword: new FormControl('', [Validators.required, Validators.minLength(1)]),
    newPassword: new FormControl('', [Validators.required, Validators.minLength(1)]),
    confirmNewPassword: new FormControl('', [Validators.required, Validators.minLength(1)])
  }, {validators: this.checkPasswords });

  constructor(private authService: AuthenticationService, private router: Router, private notificationService: NotificationService) { }

  ngOnInit() {
  }

  onSubmit() {
    if (this.changePasswordForm.invalid) {
        return;
    }

    this.authService.changePassword(this.changePasswordForm.get('oldPassword').value,
    this.changePasswordForm.get('newPassword').value, this.changePasswordForm.get('confirmNewPassword').value).subscribe(
      data => {
        this.notificationService.success('Password cambiata con successo');
        this.router.navigate(['/']);
      });
  }
}
