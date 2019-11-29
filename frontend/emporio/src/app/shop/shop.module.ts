import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { ShopRoutingModule } from '@src/app/shop/shop-routing.module';
import { ShopComponent } from '@src/app/shop/shop/shop.component';


@NgModule({
  declarations: [
    ShopComponent,
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    ShopRoutingModule
  ]
})
export class ShopModule { }
