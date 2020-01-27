import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShopSalesComponent } from '@src/app/shop/shop-sales/shop-sales.component';

describe('ShopSalesComponent', () => {
  let component: ShopSalesComponent;
  let fixture: ComponentFixture<ShopSalesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShopSalesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShopSalesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
