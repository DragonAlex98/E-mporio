import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { AuthenticationRoutingModule } from '@src/app/authentication/authentication-routing.module';
import { NativeScriptCommonModule } from 'nativescript-angular/common';
import { LoginComponent } from '@src/app/authentication/login/login.component';
import { RegistrationComponent } from '@src/app/authentication/registration/registration.component';
import { ProfileComponent } from '@src/app/authentication/profile/profile.component';


@NgModule({
  declarations: [LoginComponent, RegistrationComponent, ProfileComponent],
  imports: [
    AuthenticationRoutingModule,
    NativeScriptCommonModule
  ],
  schemas: [NO_ERRORS_SCHEMA]
})
export class AuthenticationModule { }
