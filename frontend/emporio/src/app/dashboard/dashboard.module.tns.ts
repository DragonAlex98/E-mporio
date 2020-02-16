import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { DashboardRoutingModule } from '@src/app/dashboard/dashboard-routing.module';
import { NativeScriptCommonModule } from 'nativescript-angular/common';
import { DashboardHomeComponent } from '@src/app/dashboard/dashboard-home/dashboard-home.component';
import { DashboardUsersComponent } from '@src/app/dashboard/dashboard-users/dashboard-users.component';
import { DashboardCreateAdminComponent } from '@src/app/dashboard/dashboard-create-admin/dashboard-create-admin.component';
import { DashboardCreateOperatoreComponent } from '@src/app/dashboard/dashboard-create-operatore/dashboard-create-operatore.component';


@NgModule({
  declarations: [DashboardHomeComponent, DashboardUsersComponent, DashboardCreateAdminComponent, DashboardCreateOperatoreComponent],
  imports: [
    DashboardRoutingModule,
    NativeScriptCommonModule
  ],
  exports: [
    DashboardHomeComponent,
  ],
  schemas: [NO_ERRORS_SCHEMA]
})
export class DashboardModule { }
