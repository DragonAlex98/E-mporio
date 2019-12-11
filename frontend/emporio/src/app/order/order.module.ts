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
import { MatIconModule } from '@angular/material';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';



@NgModule({
  declarations: [OrderFormComponent, OrderProductTableComponent, PhantomPageComponent, OrderProductListComponent],
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
  ]
})
export class OrderModule { }
