import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { ShopRoutingModule } from '@src/app/shop/shop-routing.module';
import { ShopComponent } from '@src/app/shop/shop/shop.component';
import { BrowserModule } from '@angular/platform-browser';
import { SearchShopComponent } from '@src/app/shop/search-shop/search-shop.component';
import { MatGridListModule } from '@angular/material/grid-list';


@NgModule({
  declarations: [
    ShopComponent,
    SearchShopComponent,
  ],
  imports: [
    CommonModule,
    BrowserModule,
    ShopRoutingModule,
    HttpClientModule,
    MatGridListModule,
  ]
})
export class ShopModule { }
