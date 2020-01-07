import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderToDeliveryDetailsComponent } from '@src/app/order/order-to-delivery-details/order-to-delivery-details.component';

describe('OrderToDeliveryDetailsComponent', () => {
  let component: OrderToDeliveryDetailsComponent;
  let fixture: ComponentFixture<OrderToDeliveryDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrderToDeliveryDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderToDeliveryDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
