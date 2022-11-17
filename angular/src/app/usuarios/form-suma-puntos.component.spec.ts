import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormSumaPuntosComponent } from './form-suma-puntos.component';

describe('FormSumaPuntosComponent', () => {
  let component: FormSumaPuntosComponent;
  let fixture: ComponentFixture<FormSumaPuntosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormSumaPuntosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormSumaPuntosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
