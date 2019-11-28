import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InsertProductComponent } from './insert-product/insert-product.component';


const routes: Routes = [
  {
    path : 'inserisciProdotto',
    component : InsertProductComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductRoutingModule { }
