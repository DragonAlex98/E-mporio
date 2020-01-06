import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderToDeliveryComponent } from '@src/app/order/order-to-delivery/order-to-delivery.component';

describe('OrderToDeliveryComponent', () => {
  let component: OrderToDeliveryComponent;
  let fixture: ComponentFixture<OrderToDeliveryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrderToDeliveryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderToDeliveryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
