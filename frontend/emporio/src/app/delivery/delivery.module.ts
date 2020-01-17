import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DeliveryRoutingModule } from '@src/app/delivery/delivery-routing.module';
import { CreateDeliveryComponent } from '@src/app/delivery/create-delivery/create-delivery.component';
import { MaterialModule } from '@src/app/material/material.module';
import { HttpClientModule } from '@angular/common/http';
import { DeliveryListComponent } from '@src/app/delivery/delivery-list/delivery-list.component';
import { DeliveryDetailsComponent } from '@src/app/delivery/delivery-details/delivery-details.component';


@NgModule({
  declarations: [CreateDeliveryComponent, DeliveryListComponent, DeliveryDetailsComponent],
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
