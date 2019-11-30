import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { ShopRoutingModule } from '@src/app/shop/shop-routing.module';
import { ShopComponent } from '@src/app/shop/shop/shop.component';
import { ShopListComponent } from '@src/app/shop/shop-list/shop-list.component';
import { BrowserModule } from '@angular/platform-browser';


@NgModule({
  declarations: [
    ShopComponent,
  ],
  imports: [
    CommonModule,
    BrowserModule,
    ShopRoutingModule,
    HttpClientModule,
  ]
})
export class ShopModule { }
