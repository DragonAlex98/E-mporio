import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShopCatalogComponent } from '@src/app/shop/shop-catalog/shop-catalog.component';

describe('ShopCatalogComponent', () => {
  let component: ShopCatalogComponent;
  let fixture: ComponentFixture<ShopCatalogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShopCatalogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShopCatalogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
