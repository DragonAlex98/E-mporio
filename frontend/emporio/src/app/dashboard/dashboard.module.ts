import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { DashboardRoutingModule } from '@src/app/dashboard/dashboard-routing.module';
import { DashboardHomeComponent } from '@src/app/dashboard/dashboard-home/dashboard-home.component';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '@src/app/material/material.module';
import { DashboardUsersComponent } from '@src/app/dashboard/dashboard-users/dashboard-users.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DashboardCreateAdminComponent } from '@src/app/dashboard/dashboard-create-admin/dashboard-create-admin.component';
import { DashboardCreateOperatoreComponent } from '@src/app/dashboard/dashboard-create-operatore/dashboard-create-operatore.component';


@NgModule({
  declarations: [DashboardHomeComponent, DashboardUsersComponent, DashboardCreateAdminComponent, DashboardCreateOperatoreComponent],
  imports: [
    CommonModule,
    DashboardRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  exports: [
    DashboardHomeComponent,
    DashboardUsersComponent,
  ]
})
export class DashboardModule { }
