import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { DashboardRoutingModule } from '@src/app/dashboard/dashboard-routing.module';
import { NativeScriptCommonModule } from 'nativescript-angular/common';
import { DashboardHomeComponent } from '@src/app/dashboard/dashboard-home/dashboard-home.component';


@NgModule({
  declarations: [DashboardHomeComponent],
  imports: [
    DashboardRoutingModule,
    NativeScriptCommonModule
  ],
  schemas: [NO_ERRORS_SCHEMA]
})
export class DashboardModule { }
