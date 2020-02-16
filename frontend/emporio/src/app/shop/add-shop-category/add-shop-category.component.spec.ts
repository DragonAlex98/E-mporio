import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddShopCategoryComponent } from '@src/app/shop/add-shop-category/add-shop-category.component';

describe('AddShopCategoryComponent', () => {
  let component: AddShopCategoryComponent;
  let fixture: ComponentFixture<AddShopCategoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddShopCategoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddShopCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
