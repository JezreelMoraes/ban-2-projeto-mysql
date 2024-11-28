import { Component } from '@angular/core';
import { BreadcrumbItemComponent } from "../../components/breadcrumb-item/breadcrumb-item.component";
import { BreadcrumbComponent } from "../../components/breadcrumb/breadcrumb.component";
import { ButtonComponent } from "../../components/button/button.component";
import { ContainerComponent } from "../../components/container/container.component";
import { IllustrationComponent } from "../../components/illustration/illustration.component";
import { Router, RouterLink } from '@angular/router';
import { WorkoutModel } from '../../shared/model/workout-model';
import { WorkoutService } from '../../shared/services/workout.service';
import { ErrorResponse } from '../../shared/http/error-response';
import { ListComponent } from "../../components/list/list.component";
import { ListItemComponent } from "../../components/list-item/list-item.component";

@Component({
  selector: 'app-workouts',
  standalone: true,
    imports: [
    BreadcrumbComponent,
    BreadcrumbItemComponent,
    ContainerComponent,
    IllustrationComponent,
    ButtonComponent,
    RouterLink,
    ListComponent,
    ListItemComponent
],
  templateUrl: './workouts-page.component.html',
  styleUrl: './workouts-page.component.scss'
})
export class WorkoutsPageComponent {

    workoutList: WorkoutModel[] = [];

    constructor(private _workoutsService: WorkoutService, private router: Router) {
        this._workoutsService.listWorkouts().subscribe({
            next: (response: WorkoutModel[]) => {
                this.workoutList = response;
            },
            error: (response: ErrorResponse) => {
                alert(`Erro: ${ response.message }`);
            }
        })
    }

    editWorkout(workout: WorkoutModel) {
        this.router.navigateByUrl("/app/workouts/edit/" + workout.id);
    }

    deleteWorkout(workout: WorkoutModel) {
        if (confirm("Deseja realmente deletar este treino?")) {

            this._workoutsService.deleteWorkout(workout.id).subscribe({
                next: () => {
                    this.workoutList = this.workoutList.filter((item) => item.id !== workout.id);
                    alert("Treino deletado com sucesso!");
                },
                error: (error: ErrorResponse) => {
                    alert(`Erro: ${ error.message }`);
                }
            });
        }
    }
}
