import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../authentication/helpers/auth.guard';
import { Role } from '@src/app/authentication/models/role';
import { OrderToDeliveryComponent } from '../order/order-to-delivery/order-to-delivery.component';
import { DeliveryListComponent } from './delivery-list/delivery-list.component';


const routes: Routes = [
  {
    path : 'orders-to-delivery',
    component : OrderToDeliveryComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.Fattorino] }
  },
  {
    path : 'delivery-list',
    component : DeliveryListComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.Fattorino] }
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DeliveryRoutingModule { }
