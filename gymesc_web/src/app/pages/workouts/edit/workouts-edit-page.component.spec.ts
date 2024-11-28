import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkoutsEditPageComponent } from './workouts-edit-page.component';

describe('WorkoutsAddPageComponent', () => {
  let component: WorkoutsEditPageComponent;
  let fixture: ComponentFixture<WorkoutsEditPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WorkoutsEditPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WorkoutsEditPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
