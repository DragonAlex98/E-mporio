import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { DashboardRoutingModule } from '@src/app/dashboard/dashboard-routing.module';
import { DashboardHomeComponent } from '@src/app/dashboard/dashboard-home/dashboard-home.component';
import { DashboardNavbarComponent } from '@src/app/dashboard/dashboard-navbar/dashboard-navbar.component';
import { CommonModule } from '@angular/common';


@NgModule({
  declarations: [DashboardHomeComponent, DashboardNavbarComponent],
  imports: [
    CommonModule,
    DashboardRoutingModule,
  ],
  exports: [
    DashboardHomeComponent,
    DashboardNavbarComponent
  ]
})
export class DashboardModule { }
