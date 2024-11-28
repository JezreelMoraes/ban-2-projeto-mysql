import { Injectable } from '@angular/core';
import { BaseHttp } from '../http/base-http';
import { ExerciseModel } from '../model/exercise-model';

@Injectable({ providedIn: 'root' })
export class ExerciseService extends BaseHttp {

    protected getBasePath(): string {
        return "/exercise"
    }

    getById(id: number) {
        return this.get<any>(
            this.secureUrl('/' + id)
        );
    }

    listExercises() {
        return this.get<any>(
            this.secureUrl()
        );
    }

    createExercise(exerciseData: ExerciseModel) {
        return this.post<any>(
            this.secureUrl(), exerciseData
        );
    }

    updateExercise(exerciseData: ExerciseModel) {
        return this.put<any>(
            this.secureUrl(), exerciseData
        );
    }

    deleteExercise(id: number) {
        return this.delete<any>(
            this.secureUrl('/' + id)
        );
    }

    listCategories() {
        return this.get<any>(
            this.secureUrl('/list-category-by-user')
        );
    }
}
