import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { DeliveryRoutingModule } from '@src/app/delivery/delivery-routing.module';
import { NativeScriptCommonModule } from 'nativescript-angular/common';
import { CreateDeliveryComponent } from '@src/app/delivery/create-delivery/create-delivery.component';
import { DeliveryListComponent } from '@src/app/delivery/delivery-list/delivery-list.component';


@NgModule({
  declarations: [CreateDeliveryComponent, DeliveryListComponent],
  imports: [
    DeliveryRoutingModule,
    NativeScriptCommonModule
  ],
  schemas: [NO_ERRORS_SCHEMA]
})
export class DeliveryModule { }
