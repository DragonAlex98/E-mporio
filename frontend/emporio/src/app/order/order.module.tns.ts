import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { OrderRoutingModule } from '@src/app/order/order-routing.module';
import { NativeScriptCommonModule } from 'nativescript-angular/common';
import { OrderFormComponent } from '@src/app/order/order-form/order-form.component';
import { OrderProductListComponent } from '@src/app/order/order-product-list/order-product-list.component';
import { PhantomPageComponent } from '@src/app/order/phantom-page/phantom-page.component';


@NgModule({
  declarations: [OrderFormComponent, OrderProductListComponent, PhantomPageComponent],
  imports: [
    OrderRoutingModule,
    NativeScriptCommonModule
  ],
  schemas: [NO_ERRORS_SCHEMA]
})
export class OrderModule { }