import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { OrderRoutingModule } from '@src/app/order/order-routing.module';
import { NativeScriptCommonModule } from 'nativescript-angular/common';
import { OrderFormComponent } from '@src/app/order/order-form/order-form.component';


@NgModule({
  declarations: [OrderFormComponent],
  imports: [
    OrderRoutingModule,
    NativeScriptCommonModule
  ],
  schemas: [NO_ERRORS_SCHEMA]
})
export class OrderModule { }
