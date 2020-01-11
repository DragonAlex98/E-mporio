import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OrderProductListComponent } from '../order/order-product-list/order-product-list.component';
import { AuthGuard } from '../authentication/helpers/auth.guard';
import { Role } from '@src/app/authentication/models/role';


const routes: Routes = [
  {
    path : 'orders-to-delivery',
    component : OrderProductListComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.Fattorino] }
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DeliveryRoutingModule { }
