import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InsertProductComponent } from './insert-product/insert-product.component';
import { SearchProductPageComponent } from './search-product-page/search-product-page.component';
import { AuthGuard } from '../authentication/helpers/auth.guard';
import { Role } from '@src/app/authentication/models/role';
import { AddProductCategoryComponent } from './add-product-category/add-product-category.component';


const routes: Routes = [
  {
    path : 'new-product',
    component : InsertProductComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.Titolare, Role.Dipendente] }
  },
  {
    path : 'add-product-category',
    component : AddProductCategoryComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.Titolare, Role.Dipendente] }
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductRoutingModule { }
