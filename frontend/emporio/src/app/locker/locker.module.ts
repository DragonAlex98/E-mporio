import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LockerRoutingModule } from '@src/app/locker/locker-routing.module';
import { LockerSelectorComponent } from '@src/app/locker/locker-selector/locker-selector.component';
import { MaterialModule } from '@src/app/material/material.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { LockerDetailComponent } from '@src/app/locker/locker-detail/locker-detail.component';


@NgModule({
  declarations: [LockerSelectorComponent, LockerDetailComponent],
  imports: [
    CommonModule,
    LockerRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    MaterialModule
  ],
  exports: [
    LockerSelectorComponent
  ],
})
export class LockerModule { }
