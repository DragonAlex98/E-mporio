import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrderFormComponent } from '@src/app/order/order-form/order-form.component';
import { OrderRoutingModule } from '@src/app/order/order-routing.module';
import { MaterialModule } from '@src/app/material/material.module';
import { OrderProductListComponent } from '@src/app/order/order-product-list/order-product-list.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { OrderProductTableComponent } from '@src/app/order/order-product-table/order-product-table.component';
import { PhantomPageComponent } from '@src/app/order/phantom-page/phantom-page.component';
import { MatIconModule, MatDialogModule } from '@angular/material';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { OrderToDeliveryComponent } from '@src/app/order/order-to-delivery/order-to-delivery.component';
import { OrderToDeliveryDetailsComponent } from '@src/app/order/order-to-delivery-details/order-to-delivery-details.component';
import { OrderComponent } from '@src/app/order/order/order.component';
import { LockerModule } from '../locker/locker.module';
import { LockerSelectorComponent } from '../locker/locker-selector/locker-selector.component';
import { DeliveryModule } from '../delivery/delivery.module';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [
    OrderFormComponent,
    OrderProductTableComponent,
    PhantomPageComponent,
    OrderProductListComponent,
    OrderToDeliveryComponent,
    OrderToDeliveryDetailsComponent,
    OrderComponent
  ],
  imports: [
    MaterialModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    OrderRoutingModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatIconModule,
    LockerModule,
    DeliveryModule,
    HttpClientModule
  ]
})
export class OrderModule { }
