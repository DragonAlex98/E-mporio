import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { AuthenticationRoutingModule } from '@src/app/authentication/authentication-routing.module';
import { NativeScriptCommonModule } from 'nativescript-angular/common';
import { LoginComponent } from '@src/app/authentication/login/login.component';


@NgModule({
  declarations: [LoginComponent],
  imports: [
    AuthenticationRoutingModule,
    NativeScriptCommonModule
  ],
  schemas: [NO_ERRORS_SCHEMA]
})
export class AuthenticationModule { }
