import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { LockerRoutingModule } from '@src/app/locker/locker-routing.module';
import { NativeScriptCommonModule } from 'nativescript-angular/common';
import { LockerSelectorComponent } from '@src/app/locker/locker-selector/locker-selector.component';


@NgModule({
  declarations: [LockerSelectorComponent],
  imports: [
    LockerRoutingModule,
    NativeScriptCommonModule
  ],
  schemas: [NO_ERRORS_SCHEMA]
})
export class LockerModule { }
