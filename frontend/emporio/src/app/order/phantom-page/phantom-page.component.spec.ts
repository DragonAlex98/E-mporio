import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PhantomPageComponent } from '@src/app/order/phantom-page/phantom-page.component';

describe('PhantomPageComponent', () => {
  let component: PhantomPageComponent;
  let fixture: ComponentFixture<PhantomPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PhantomPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PhantomPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
