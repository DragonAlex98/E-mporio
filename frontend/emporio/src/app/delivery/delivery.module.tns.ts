import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { DeliveryRoutingModule } from './delivery-routing.module';
import { NativeScriptCommonModule } from 'nativescript-angular/common';


@NgModule({
  declarations: [],
  imports: [
    DeliveryRoutingModule,
    NativeScriptCommonModule
  ],
  schemas: [NO_ERRORS_SCHEMA]
})
export class DeliveryModule { }
