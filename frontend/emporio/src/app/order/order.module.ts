import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OrderFormComponent } from '@src/app/order/order-form/order-form.component';
import { OrderRoutingModule } from './order-routing.module';
import { MatGridListModule } from '@angular/material/grid-list';
import {MatStepperModule} from '@angular/material/stepper';
import {MatFormFieldModule} from '@angular/material/form-field';



@NgModule({
  declarations: [OrderFormComponent],
  imports: [
    CommonModule,
    OrderRoutingModule,
    MatGridListModule,
    MatStepperModule,
    MatFormFieldModule,
  ]
})
export class OrderModule { }
