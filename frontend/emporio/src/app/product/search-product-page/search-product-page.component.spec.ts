import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchProductPageComponent } from '@src/app/product/search-product-page/search-product-page.component';

describe('SearchProductPageComponent', () => {
  let component: SearchProductPageComponent;
  let fixture: ComponentFixture<SearchProductPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchProductPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchProductPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
