import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashboardRoutingModule } from '@src/app/dashboard/dashboard-routing.module';
import { DashboardHomeComponent } from '@src/app/dashboard/dashboard-home/dashboard-home.component';


@NgModule({
  declarations: [DashboardHomeComponent],
  imports: [
    CommonModule,
    DashboardRoutingModule
  ]
})
export class DashboardModule { }
