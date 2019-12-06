import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { ShopRoutingModule } from '@src/app/shop/shop-routing.module';
import { NativeScriptCommonModule } from 'nativescript-angular/common';
import { ShopComponent } from '@src/app/shop/shop/shop.component';
import { ShopListComponent } from '@src/app/shop/shop-list/shop-list.component';
import { SearchShopComponent } from '@src/app/shop/search-shop/search-shop.component';


@NgModule({
  declarations: [ShopComponent, ShopListComponent, SearchShopComponent],
  imports: [
    ShopRoutingModule,
    NativeScriptCommonModule
  ],
  schemas: [NO_ERRORS_SCHEMA]
})
export class ShopModule { }
