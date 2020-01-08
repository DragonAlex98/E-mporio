import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LockerSelectorComponent } from '@src/app/locker/locker-selector/locker-selector.component';

describe('LockerSelectorComponent', () => {
  let component: LockerSelectorComponent;
  let fixture: ComponentFixture<LockerSelectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LockerSelectorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LockerSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
