import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardCreateOperatoreComponent } from '@src/app/dashboard/dashboard-create-operatore/dashboard-create-operatore.component';

describe('DashboardCreateOperatoreComponent', () => {
  let component: DashboardCreateOperatoreComponent;
  let fixture: ComponentFixture<DashboardCreateOperatoreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DashboardCreateOperatoreComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DashboardCreateOperatoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
