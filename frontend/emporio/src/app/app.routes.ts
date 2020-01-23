import { Routes } from '@angular/router';

import { HomeComponent } from '@src/app/home/home.component';
import { SearchProductPageComponent } from '@src/app/product/search-product-page/search-product-page.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { SearchShopComponent } from './shop/search-shop/search-shop.component';
import { OrderFormComponent } from './order/order-form/order-form.component';
import { PhantomPageComponent } from './order/phantom-page/phantom-page.component';
import { LoginComponent } from './authentication/login/login.component';
import { AuthGuard } from './authentication/helpers/auth.guard';
import { RegistrationComponent } from './authentication/registration/registration.component';
import { OrderToDeliveryComponent } from './order/order-to-delivery/order-to-delivery.component';
import { Role } from './authentication/models/role';
import { DashboardHomeComponent } from './dashboard/dashboard-home/dashboard-home.component';
import { DashboardGuard } from './authentication/helpers/dashboard.guard';
import { DashboardUsersComponent } from './dashboard/dashboard-users/dashboard-users.component';
import { InsertLockerComponent } from './locker/insert-locker/insert-locker.component';


export const routes: Routes = [
  {
      path: '',
      redirectTo: '/home',
      pathMatch: 'full',
  },
  {
    path: 'order/phantom-page',
    component: PhantomPageComponent,
  },
  {
      path: 'home',
      component: HomeComponent,
      canActivate: [DashboardGuard]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'registration',
    component: RegistrationComponent
  },
  {
    path: 'cerca-prodotto',
    component: SearchProductPageComponent,
  },
  {
    path: 'cerca-attivita',
    component: SearchShopComponent,
  },
  {
    path: 'new-order',
    component: OrderFormComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.Titolare, Role.Dipendente] }
  },
  {
    path: 'dashboard',
    children: [
      {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full',
      },
      {
        path: 'home',
        component: DashboardHomeComponent,
      },
      {
        path: 'users',
        component: DashboardUsersComponent,
      },
      {
        path: 'add-locker',
        component: InsertLockerComponent,
      },
      {
        path: '**',
        component: PageNotFoundComponent,
      }
    ],
    canActivate: [AuthGuard],
    data: { roles: [Role.Admin, Role.OperatoreSistema] }
  },
  {
    path: '**',
    component: PageNotFoundComponent,
  },
];
