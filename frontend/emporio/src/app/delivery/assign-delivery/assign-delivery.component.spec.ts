import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignDeliveryComponent } from '@src/app/delivery/assign-delivery/assign-delivery.component';

describe('AssignDeliveryComponent', () => {
  let component: AssignDeliveryComponent;
  let fixture: ComponentFixture<AssignDeliveryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssignDeliveryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssignDeliveryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
