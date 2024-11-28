import { CommonModule, NgFor, NgIf } from "@angular/common";
import { Component, OnInit } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { Router, RouterLink } from "@angular/router";
import { IDropdownSettings, NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown'; // Adicionado
import { BreadcrumbItemComponent } from "../../../components/breadcrumb-item/breadcrumb-item.component";
import { BreadcrumbComponent } from "../../../components/breadcrumb/breadcrumb.component";
import { ButtonComponent } from "../../../components/button/button.component";
import { ContainerComponent } from "../../../components/container/container.component";
import { FormComponent } from "../../../components/form/form.component";
import { IllustrationComponent } from "../../../components/illustration/illustration.component";
import { ExerciseModel } from "../../../shared/model/exercise-model";
import { WorkoutModel } from "../../../shared/model/workout-model";
import { ExerciseService } from "../../../shared/services/exercise.service";
import { WorkoutService } from "../../../shared/services/workout.service";

@Component({
    selector: 'app-workouts-add-page',
    standalone: true,
    imports: [
        BreadcrumbComponent,
        BreadcrumbItemComponent,
        ButtonComponent,
        ContainerComponent,
        IllustrationComponent,
        FormComponent,
        RouterLink,
        FormsModule,
        NgFor,
        NgIf,
        CommonModule,
        NgMultiSelectDropDownModule
    ],
    templateUrl: './workouts-add-page.component.html',
    styleUrl: './workouts-add-page.component.scss'
})
export class WorkoutsAddPageComponent implements OnInit {

    workoutData: WorkoutModel;
    exerciseList: ExerciseModel[];
    dropdownSettings: IDropdownSettings = {};

    constructor(private router: Router, private _exerciseService: ExerciseService, private _workoutService: WorkoutService) {
        this.workoutData = new WorkoutModel();
        this.exerciseList = [];
    }

    ngOnInit() {
        this._exerciseService.listExercises().subscribe({
            next: (response: any) => {
                this.exerciseList = response;
            },
            error: (error) => {
                alert(`Erro: ${error.message}`);
            }
        });

        this.dropdownSettings = {
            singleSelection: false,
            idField: 'id',  // Ajuste conforme a estrutura de ExerciseData
            textField: 'name', // Ajuste conforme a estrutura de ExerciseData
            selectAllText: 'Selecionar Todos',
            unSelectAllText: 'Desselecionar Todos',
            itemsShowLimit: 3,
            allowSearchFilter: true
        };
    }

    doCreateWorkout() {
        this._workoutService.createWorkout(this.workoutData).subscribe({
            next: () => {
                alert("Treino criado com sucesso!");
                this.router.navigateByUrl("/app/workouts");
            },
            error: (error) => {
                alert(`Erro: ${error.message}`);
            }
        });
    }
}
