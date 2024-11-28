import { Component } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { ActivatedRoute, Router, RouterLink } from "@angular/router";
import { BreadcrumbItemComponent } from "../../../components/breadcrumb-item/breadcrumb-item.component";
import { BreadcrumbComponent } from "../../../components/breadcrumb/breadcrumb.component";
import { ButtonComponent } from "../../../components/button/button.component";
import { ContainerComponent } from "../../../components/container/container.component";
import { FormComponent } from "../../../components/form/form.component";
import { IllustrationComponent } from "../../../components/illustration/illustration.component";
import { ExerciseModel } from "../../../shared/model/exercise-model";
import { ExerciseService } from "../../../shared/services/exercise.service";
import { ErrorResponse } from '../../../shared/http/error-response';
import { CategoryModel } from "../../../shared/model/category-model";
import { ListItemConfig, SelectComponent} from "../../../components/select/select.component";

@Component({
    selector: 'app-exercices-edit-page',
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
        SelectComponent
    ],
    templateUrl: './exercises-edit-page.component.html',
    styleUrl: './exercises-edit-page.component.scss'
})
export class ExercisesEditPageComponent {

    itemId!: number;

    exerciseData: ExerciseModel

    categoryList: CategoryModel[];

    listItemConfig = new ListItemConfig('id', 'name')

    constructor(private _exercisesService: ExerciseService, private route: ActivatedRoute, private router: Router) {
        this.exerciseData = new ExerciseModel();
        this.categoryList = [];
    }

    ngOnInit(): void {
        this._exercisesService.listCategories().subscribe({
            next: (response: CategoryModel[]) => {
                this.categoryList = response;
            },
            error: () => {}
        })

        this.itemId = Number(this.route.snapshot.paramMap.get('id'));
        this.loadItemData();
    }

    loadItemData(): void {
        this._exercisesService.getById(this.itemId).subscribe({
            next: (response: ExerciseModel) => {
                this.exerciseData = response;
            },
            error: (error: ErrorResponse) => {
                alert('Erro ao carregar item: ' + error.message);
            }
        });
    }

    doUpdateExercise() {
        this._exercisesService.updateExercise(this.exerciseData).subscribe({
            next: () => {
                alert("Exercício atualizado com sucesso!");
                this.router.navigateByUrl("/app/exercises");
            },
            error: (error) => {
                alert(`Erro: ${error.message}`);
            }
        })
    }

    protected readonly CategoryModel = CategoryModel;
}

