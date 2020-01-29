import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMarketingManagerComponent } from '@src/app/shop/add-marketing-manager/add-marketing-manager.component';

describe('AddEmployeeComponent', () => {
  let component: AddMarketingManagerComponent;
  let fixture: ComponentFixture<AddMarketingManagerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddMarketingManagerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddMarketingManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
