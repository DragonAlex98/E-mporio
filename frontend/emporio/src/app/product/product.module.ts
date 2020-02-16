import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { ProductRoutingModule } from '@src/app/product/product-routing.module';
import { InsertProductComponent } from '@src/app/product/insert-product/insert-product.component';
import { InsertProductFormComponent } from '@src/app/product/insert-product-form/insert-product-form.component';
import { ProductComponent } from '@src/app/product/product/product.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SearchProductPageComponent } from '@src/app/product/search-product-page/search-product-page.component';
import { MaterialModule } from '@src/app/material/material.module';
import { AddProductCategoryComponent } from '@src/app/product/add-product-category/add-product-category.component';


@NgModule({
  declarations: [
    InsertProductComponent,
    InsertProductFormComponent,
    ProductComponent,
    SearchProductPageComponent,
    AddProductCategoryComponent,
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    ProductRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
  ]
})
export class ProductModule { }
