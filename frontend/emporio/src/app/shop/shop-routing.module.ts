import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InsertShopComponent } from './insert-shop/insert-shop.component';
import { AddEmployeeComponent } from './add-employee/add-employee.component';
import { AuthGuard } from '../authentication/helpers/auth.guard';
import { Role } from '../authentication/models/role';
import { ShopDetailComponent } from './shop-detail/shop-detail.component';
import { AddMarketingManagerComponent } from './add-marketing-manager/add-marketing-manager.component';
import { ShopSalesComponent } from './shop-sales/shop-sales.component';
import { AddShopCategoryComponent } from './add-shop-category/add-shop-category.component';

const routes: Routes = [
  {
    path: 'new-shop',
    component: InsertShopComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.Titolare] }
  },
  {
    path: 'shops/:piva',
    component: ShopDetailComponent
  },
  {
    path: 'add-employee',
    component: AddEmployeeComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.Titolare] }
  },
  {
    path: 'add-marketing-manager',
    component: AddMarketingManagerComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.Titolare] }
  },
  {
    path: 'shop-sales',
    component: ShopSalesComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.Titolare, Role.GestoreMarketing] }
  },
  {
    path: 'add-shop-category',
    component: AddShopCategoryComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.Titolare] }
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ShopRoutingModule { }
