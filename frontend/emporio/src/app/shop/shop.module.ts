import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { ShopRoutingModule } from '@src/app/shop/shop-routing.module';
import { ShopComponent } from '@src/app/shop/shop/shop.component';
import { SearchShopComponent } from '@src/app/shop/search-shop/search-shop.component';
import { MaterialModule } from '../../app/material/material.module';
import { InsertShopComponent } from '@src/app/shop/insert-shop/insert-shop.component';
import { InsertShopFormComponent } from '@src/app/shop/insert-shop-form/insert-shop-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddEmployeeComponent } from '@src/app/shop/add-employee/add-employee.component';
import { ShopDetailComponent } from '@src/app/shop/shop-detail/shop-detail.component';
import { AddProductComponent } from '@src/app/shop/add-product/add-product.component';
import { ShopCatalogComponent } from '@src/app/shop/shop-catalog/shop-catalog.component';


@NgModule({
  declarations: [
    ShopComponent,
    SearchShopComponent,
    InsertShopComponent,
    InsertShopFormComponent,
    ShopDetailComponent,
    AddProductComponent,
    ShopCatalogComponent,
    AddEmployeeComponent,
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    ShopRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
  ]
})
export class ShopModule { }
