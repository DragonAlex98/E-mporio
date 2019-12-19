import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { AuthenticationRoutingModule } from './authentication-routing.module';
import { NativeScriptCommonModule } from 'nativescript-angular/common';


@NgModule({
  declarations: [],
  imports: [
    AuthenticationRoutingModule,
    NativeScriptCommonModule
  ],
  schemas: [NO_ERRORS_SCHEMA]
})
export class AuthenticationModule { }
