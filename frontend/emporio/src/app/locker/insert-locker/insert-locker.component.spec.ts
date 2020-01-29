import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InsertLockerComponent } from '@src/app/locker/insert-locker/insert-locker.component';

describe('InsertLockerComponent', () => {
  let component: InsertLockerComponent;
  let fixture: ComponentFixture<InsertLockerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InsertLockerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InsertLockerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
