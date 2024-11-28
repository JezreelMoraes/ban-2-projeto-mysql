import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExercisesAddPageComponent } from './exercises-add-page.component';

describe('ExercicesAddPageComponent', () => {
  let component: ExercisesAddPageComponent;
  let fixture: ComponentFixture<ExercisesAddPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExercisesAddPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExercisesAddPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
