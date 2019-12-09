import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrderFormComponent } from '@src/app/order/order-form/order-form.component';
import { OrderRoutingModule } from './order-routing.module';
import { MaterialModule } from '../material/material.module';



@NgModule({
  declarations: [OrderFormComponent],
  imports: [
    CommonModule,
    OrderRoutingModule,
    MaterialModule,
  ]
})
export class OrderModule { }
