import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchShopComponent } from './search-shop/search-shop.component';
import { InsertShopComponent } from './insert-shop/insert-shop.component';

const routes: Routes = [
  {
    path: 'new-shop',
    component: InsertShopComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ShopRoutingModule { }
