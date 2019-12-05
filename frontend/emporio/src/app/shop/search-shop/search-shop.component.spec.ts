import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchShopComponent } from '@src/app/shop/search-shop/search-shop.component';

describe('SearchShopComponent', () => {
  let component: SearchShopComponent;
  let fixture: ComponentFixture<SearchShopComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchShopComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchShopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
