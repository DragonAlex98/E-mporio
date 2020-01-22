import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { DashboardRoutingModule } from '@src/app/dashboard/dashboard-routing.module';
import { DashboardHomeComponent } from '@src/app/dashboard/dashboard-home/dashboard-home.component';
import { DashboardNavbarComponent } from '@src/app/dashboard/dashboard-navbar/dashboard-navbar.component';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '@src/app/material/material.module';
import { DashboardUsersComponent } from '@src/app/dashboard/dashboard-users/dashboard-users.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [DashboardHomeComponent, DashboardNavbarComponent, DashboardUsersComponent],
  imports: [
    CommonModule,
    DashboardRoutingModule,
    MaterialModule,
    FormsModule,
  ],
  exports: [
    DashboardHomeComponent,
    DashboardNavbarComponent,
    DashboardUsersComponent,
  ]
})
export class DashboardModule { }
