import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { LockerRoutingModule } from '@src/app/locker/locker-routing.module';
import { NativeScriptCommonModule } from 'nativescript-angular/common';
import { LockerSelectorComponent } from '@src/app/locker/locker-selector/locker-selector.component';
import { LockerDetailComponent } from '@src/app/locker/locker-detail/locker-detail.component';
import { InsertLockerComponent } from '@src/app/locker/insert-locker/insert-locker.component';


@NgModule({
  declarations: [LockerSelectorComponent, LockerDetailComponent, InsertLockerComponent],
  imports: [
    LockerRoutingModule,
    NativeScriptCommonModule
  ],
  schemas: [NO_ERRORS_SCHEMA]
})
export class LockerModule { }
