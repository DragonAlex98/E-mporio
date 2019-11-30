import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { ProductRoutingModule } from '@src/app/product/product-routing.module';
import { InsertProductComponent } from '@src/app/product/insert-product/insert-product.component';
import { InsertProductFormComponent } from '@src/app/product/insert-product-form/insert-product-form.component';
import { ProductComponent } from '@src/app/product/product/product.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    InsertProductComponent,
    InsertProductFormComponent,
    ProductComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    ProductRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class ProductModule { }
