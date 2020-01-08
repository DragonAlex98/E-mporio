import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LockerRoutingModule } from '@src/app/locker/locker-routing.module';
import { LockerSelectorComponent } from '@src/app/locker/locker-selector/locker-selector.component';
import { MaterialModule } from '../material/material.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';


@NgModule({
  declarations: [LockerSelectorComponent],
  imports: [
    CommonModule,
    LockerRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    MaterialModule
  ]
})
export class LockerModule { }
