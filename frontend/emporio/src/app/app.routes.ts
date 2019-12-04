import { Routes } from '@angular/router';

import { HomeComponent } from '@src/app/home/home.component';
import { SearchProductPageComponent } from '@src/app/product/search-product-page/search-product-page.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

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
    path: 'product/search',
    component: SearchProductPageComponent,
  },
  {
    path: '**',
    component: PageNotFoundComponent,
  },
];
