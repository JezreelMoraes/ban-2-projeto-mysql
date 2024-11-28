import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExercisesEditPageComponent } from './exercises-edit-page.component';

describe('ExercicesAddPageComponent', () => {
  let component: ExercisesEditPageComponent;
  let fixture: ComponentFixture<ExercisesEditPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExercisesEditPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExercisesEditPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
