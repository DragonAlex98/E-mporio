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
  },
  {
    path: 'orders-to-delivery',
    component: OrderToDeliveryComponent
  },
  {
    path: '**',
    component: PageNotFoundComponent,
  },
];
