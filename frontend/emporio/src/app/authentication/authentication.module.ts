import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthenticationRoutingModule } from '@src/app/authentication/authentication-routing.module';
import { LoginComponent } from '@src/app/authentication/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RegistrationComponent } from '@src/app/authentication/registration/registration.component';
import { MaterialModule } from '@src/app/material/material.module';
import { ProfileComponent } from '@src/app/authentication/profile/profile.component';



@NgModule({
  declarations: [LoginComponent, RegistrationComponent, ProfileComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    AuthenticationRoutingModule,
    MaterialModule
  ]
})
export class AuthenticationModule { }
