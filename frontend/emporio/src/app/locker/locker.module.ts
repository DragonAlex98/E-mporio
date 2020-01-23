import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LockerRoutingModule } from '@src/app/locker/locker-routing.module';
import { LockerSelectorComponent } from '@src/app/locker/locker-selector/locker-selector.component';
import { MaterialModule } from '@src/app/material/material.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { LockerDetailComponent } from '@src/app/locker/locker-detail/locker-detail.component';
import { InsertLockerComponent } from '@src/app/locker/insert-locker/insert-locker.component';


@NgModule({
  declarations: [LockerSelectorComponent, LockerDetailComponent, InsertLockerComponent],
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
  schemas: [NO_ERRORS_SCHEMA]
})
export class LockerModule { }
