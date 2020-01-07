import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InsertShopComponent } from './insert-shop/insert-shop.component';
import { AddEmployeeComponent } from './add-employee/add-employee.component';
import { AuthGuard } from '../authentication/helpers/auth.guard';
import { Role } from '../authentication/models/role';
import { ShopDetailComponent } from './shop-detail/shop-detail.component';

const routes: Routes = [
  {
    path: 'new-shop',
    component: InsertShopComponent,
  },
  {
    path: 'add-employee',
    component: AddEmployeeComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.Titolare] }
  },
  {
    path: 'shops/:piva',
    component: ShopDetailComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ShopRoutingModule { }
