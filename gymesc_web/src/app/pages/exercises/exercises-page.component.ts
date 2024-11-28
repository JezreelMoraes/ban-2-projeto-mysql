import { Component } from '@angular/core';
import { BreadcrumbComponent } from "../../components/breadcrumb/breadcrumb.component";
import { BreadcrumbItemComponent } from "../../components/breadcrumb-item/breadcrumb-item.component";
import { ContainerComponent } from "../../components/container/container.component";
import { IllustrationComponent } from "../../components/illustration/illustration.component";
import { ButtonComponent } from "../../components/button/button.component";
import { Router, RouterLink } from "@angular/router";
import { ListComponent } from "../../components/list/list.component";
import { ListItemComponent } from "../../components/list-item/list-item.component";
import { ExerciseService } from "../../shared/services/exercise.service";
import { ExerciseModel } from '../../shared/model/exercise-model';
import { ErrorResponse } from '../../shared/http/error-response';

@Component({
    selector: 'app-exercises',
    standalone: true,
    imports: [
        BreadcrumbComponent,
        BreadcrumbItemComponent,
        ContainerComponent,
        IllustrationComponent,
        ButtonComponent,
        RouterLink,
        ListComponent,
        ListItemComponent,
    ],
    templateUrl: './exercises-page.component.html',
    styleUrl: './exercises-page.component.scss'
})
export class ExercisesPageComponent {

    exerciseList!: ExerciseModel[]

    constructor(private _exercisesService: ExerciseService, private router: Router) {
        this._exercisesService.listExercises().subscribe({
            next: (response) => {
                this.exerciseList = response;
            },
            error: (response) => {
                alert(`Erro: ${ response.message }`);
            }
        })
    }

    editExercise(exercise: ExerciseModel) {
        this.router.navigateByUrl("/app/exercises/edit/" + exercise.id);
    }

    deleteExercise(exercise: ExerciseModel) {
        if (confirm("Deseja realmente deletar este exercício?")) {

            this._exercisesService.deleteExercise(exercise.id).subscribe({
                next: () => {
                    this.exerciseList = this.exerciseList.filter((item) => item.id !== exercise.id);
                    alert("Exercício deletado com sucesso!");
                },
                error: (error: ErrorResponse) => {
                    alert(`Erro: ${ error.message }`);
                }
            });
        }
    }
}
