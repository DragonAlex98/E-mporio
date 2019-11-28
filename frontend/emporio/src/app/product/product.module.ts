import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductRoutingModule } from '@src/app/product/product-routing.module';
import { InsertProductComponent } from '@src/app/product/insert-product/insert-product.component';
import { InsertProductFormComponent } from '@src/app/product/insert-product-form/insert-product-form.component';
import { ProductComponent } from '@src/app/product/product/product.component';


@NgModule({
  declarations: [InsertProductComponent, InsertProductFormComponent, ProductComponent],
  imports: [
    CommonModule,
    ProductRoutingModule
  ]
})
export class ProductModule { }
