import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchShopComponent } from './search-shop/search-shop.component';

const routes: Routes = [];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ShopRoutingModule { }
