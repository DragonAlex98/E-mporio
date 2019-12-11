import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InsertProductComponent } from './insert-product/insert-product.component';
import { SearchProductPageComponent } from './search-product-page/search-product-page.component';


const routes: Routes = [
  {
    path : 'new-product',
    component : InsertProductComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductRoutingModule { }
