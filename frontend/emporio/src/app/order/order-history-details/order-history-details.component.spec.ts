import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderHistoryDetailsComponent } from '@src/app/order/order-history-details/order-history-details.component';

describe('OrderHistoryDetailsComponent', () => {
  let component: OrderHistoryDetailsComponent;
  let fixture: ComponentFixture<OrderHistoryDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrderHistoryDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderHistoryDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
