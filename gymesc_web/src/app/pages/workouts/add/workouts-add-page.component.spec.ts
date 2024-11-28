import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkoutsAddPageComponent } from './workouts-add-page.component';

describe('WorkoutsAddPageComponent', () => {
  let component: WorkoutsAddPageComponent;
  let fixture: ComponentFixture<WorkoutsAddPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WorkoutsAddPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(WorkoutsAddPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
