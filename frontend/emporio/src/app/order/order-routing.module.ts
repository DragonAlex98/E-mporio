import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OrderHistoryComponent } from './order-history/order-history.component';
import { AuthGuard } from '../authentication/helpers/auth.guard';
import { Role } from '../authentication/models/role';



const routes: Routes = [
  {
    path: 'users/:username/orders',
    component: OrderHistoryComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.Acquirente] }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OrderRoutingModule { }
