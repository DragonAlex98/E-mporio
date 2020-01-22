import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { DashboardRoutingModule } from '@src/app/dashboard/dashboard-routing.module';
import { NativeScriptCommonModule } from 'nativescript-angular/common';
import { DashboardHomeComponent } from '@src/app/dashboard/dashboard-home/dashboard-home.component';
import { DashboardNavbarComponent } from '@src/app/dashboard/dashboard-navbar/dashboard-navbar.component';
import { DashboardUsersComponent } from '@src/app/dashboard/dashboard-users/dashboard-users.component';


@NgModule({
  declarations: [DashboardHomeComponent, DashboardNavbarComponent, DashboardUsersComponent],
  imports: [
    DashboardRoutingModule,
    NativeScriptCommonModule
  ],
  exports: [
    DashboardHomeComponent,
    DashboardNavbarComponent
  ],
  schemas: [NO_ERRORS_SCHEMA]
})
export class DashboardModule { }
