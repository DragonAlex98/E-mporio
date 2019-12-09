import { Routes } from '@angular/router';

import { HomeComponent } from '@src/app/home/home.component';
import { SearchProductPageComponent } from '@src/app/product/search-product-page/search-product-page.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { SearchShopComponent } from './shop/search-shop/search-shop.component';
import { OrderFormComponent } from './order/order-form/order-form.component';

export const routes: Routes = [
  {
      path: '',
      redirectTo: '/home',
      pathMatch: 'full',
  },
  {
      path: 'home',
      component: HomeComponent,
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
    path: '**',
    component: PageNotFoundComponent,
  },
];
