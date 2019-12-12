import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InsertShopComponent } from '@src/app/shop/insert-shop/insert-shop.component';

describe('InsertShopComponent', () => {
  let component: InsertShopComponent;
  let fixture: ComponentFixture<InsertShopComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InsertShopComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InsertShopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
