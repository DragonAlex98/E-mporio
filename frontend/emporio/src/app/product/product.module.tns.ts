import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { ProductRoutingModule } from '@src/app/product/product-routing.module';
import { NativeScriptCommonModule } from 'nativescript-angular/common';
import { InsertProductComponent } from '@src/app/product/insert-product/insert-product.component';
import { InsertProductFormComponent } from '@src/app/product/insert-product-form/insert-product-form.component';
import { ProductComponent } from '@src/app/product/product/product.component';
import { NativeScriptFormsModule } from 'nativescript-angular/forms';
import { NativeScriptHttpClientModule } from 'nativescript-angular/http-client';
import { SearchProductPageComponent } from '@src/app/product/search-product-page/search-product-page.component';
import { AddProductCategoryComponent } from '@src/app/product/add-product-category/add-product-category.component';


@NgModule({
  declarations: [InsertProductComponent, InsertProductFormComponent, ProductComponent, SearchProductPageComponent, AddProductCategoryComponent],
  imports: [
    ProductRoutingModule,
    NativeScriptCommonModule,
    NativeScriptFormsModule,
    NativeScriptHttpClientModule,
  ],
  schemas: [NO_ERRORS_SCHEMA]
})
export class ProductModule { }
