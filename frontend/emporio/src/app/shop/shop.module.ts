import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { ShopRoutingModule } from '@src/app/shop/shop-routing.module';
import { ShopComponent } from '@src/app/shop/shop/shop.component';
import { BrowserModule } from '@angular/platform-browser';
import { SearchShopComponent } from '@src/app/shop/search-shop/search-shop.component';
import { MaterialModule } from '@src/app/material/material.module';
import { InsertShopComponent } from '@src/app/shop/insert-shop/insert-shop.component';
import { InsertShopFormComponent } from '@src/app/shop/insert-shop-form/insert-shop-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    ShopComponent,
    SearchShopComponent,
    InsertShopComponent,
    InsertShopFormComponent,
  ],
  imports: [
    CommonModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    ShopRoutingModule,
    HttpClientModule,
  ]
})
export class ShopModule { }
