import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DeliveryRoutingModule } from '@src/app/delivery/delivery-routing.module';
import { CreateDeliveryComponent } from '@src/app/delivery/create-delivery/create-delivery.component';
import { MaterialModule } from '../material/material.module';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [CreateDeliveryComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    DeliveryRoutingModule,
    MaterialModule
  ],
  exports: [
    CreateDeliveryComponent
  ],
})
export class DeliveryModule { }
