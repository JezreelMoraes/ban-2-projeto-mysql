import { CommonModule, NgFor, NgIf } from "@angular/common";
import { Component, OnInit } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { ActivatedRoute, Router, RouterLink } from "@angular/router";
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
    selector: 'app-workouts-edit-page',
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
    templateUrl: './workouts-edit-page.component.html',
    styleUrl: './workouts-edit-page.component.scss'
})
export class WorkoutsEditPageComponent implements OnInit {

    workoutId!: number;
    workoutData: WorkoutModel;
    exerciseList: ExerciseModel[];
    dropdownSettings: IDropdownSettings = {};

    constructor(private router: Router, private route: ActivatedRoute, private _exerciseService: ExerciseService, private _workoutService: WorkoutService) {
        this.workoutData = new WorkoutModel();
        this.exerciseList = [];
    }

    ngOnInit() {
        this.workoutId = Number(this.route.snapshot.paramMap.get('id'));

        this._exerciseService.listExercises().subscribe({
            next: (response: any) => {
                this.exerciseList = response;
            },
            error: (error) => {
                alert(`Erro: ${error.message}`);
            }
        });

        this._workoutService.getById(this.workoutId).subscribe({
            next: (response: WorkoutModel) => {
                this.workoutData = response;
            },
            error: (error) => {
                alert(`Erro: ${error.message}`);
            }
        });

        this.dropdownSettings = {
            singleSelection: false,
            idField: 'id',
            textField: 'name',
            selectAllText: 'Selecionar Todos',
            unSelectAllText: 'Desselecionar Todos',
            itemsShowLimit: 3,
            allowSearchFilter: true
        };
    }

    doUpdateWorkout() {
        this._workoutService.updateWorkout(this.workoutData).subscribe({
            next: () => {
                alert("Treino editado com sucesso!");
                this.router.navigateByUrl("/app/workouts");
            },
            error: (error) => {
                alert(`Erro: ${error.message}`);
            }
        });
    }
}
