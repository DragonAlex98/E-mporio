import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddProductCategoryComponent } from '@src/app/product/add-product-category/add-product-category.component';

describe('AddProductCategoryComponent', () => {
  let component: AddProductCategoryComponent;
  let fixture: ComponentFixture<AddProductCategoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddProductCategoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddProductCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
