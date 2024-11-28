import { Component, numberAttribute, OnInit, ViewChild } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { Router, RouterLink } from "@angular/router";
import { BreadcrumbItemComponent } from "../../../components/breadcrumb-item/breadcrumb-item.component";
import { BreadcrumbComponent } from "../../../components/breadcrumb/breadcrumb.component";
import { ButtonComponent } from "../../../components/button/button.component";
import { ContainerComponent } from "../../../components/container/container.component";
import { FormComponent } from "../../../components/form/form.component";
import { IllustrationComponent } from "../../../components/illustration/illustration.component";
import { ExerciseModel } from "../../../shared/model/exercise-model";
import { ExerciseService } from "../../../shared/services/exercise.service";
import { ListItemConfig, SelectComponent } from "../../../components/select/select.component";
import { CategoryModel } from "../../../shared/model/category-model";

@Component({
    selector: 'app-exercices-add-page',
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
        SelectComponent,
    ],
    templateUrl: './exercises-add-page.component.html',
    styleUrl: './exercises-add-page.component.scss'
})
export class ExercisesAddPageComponent implements OnInit {

    exerciseData: ExerciseModel

    categoryList: CategoryModel[]

    listItemConfig = new ListItemConfig('id', 'name')

    constructor(private _exercisesService: ExerciseService, private router: Router) {
        this.exerciseData = new ExerciseModel()
        this.categoryList = []
    }

    ngOnInit() {
        this._exercisesService.listCategories().subscribe({
            next: (response: CategoryModel[]) => {
                this.categoryList = response;
            },
            error: () => {}
        })
    }

    doCreateExercise() {
        console.log(this.exerciseData)

        this._exercisesService.createExercise(this.exerciseData).subscribe({
            next: () => {
                alert("Exercício criado com sucesso!");
                this.router.navigateByUrl("/app/exercises");
            },
            error: (error) => {
                alert(`Erro: ${ error.message }`);
            }
        })
    }

    protected readonly CategoryModel = CategoryModel;
}

