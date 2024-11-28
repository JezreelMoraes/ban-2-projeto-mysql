import { Injectable } from '@angular/core';
import { BaseHttp } from '../http/base-http';
import { WorkoutModel } from '../model/workout-model';

@Injectable({ providedIn: 'root' })
export class WorkoutService extends BaseHttp {

    protected getBasePath(): string {
        return "/workout"
    }

    getById(id: number) {
        return this.get<WorkoutModel>(
            this.secureUrl('/' + id)
        );
    }

    createWorkout(workoutData: WorkoutModel) {
        return this.post<any>(
            this.secureUrl(), workoutData
        );
    }

    updateWorkout(workoutData: WorkoutModel) {
        return this.put<any>(
            this.secureUrl(), workoutData
        );
    }

    deleteWorkout(id: number) {
        return this.delete<any>(
            this.secureUrl('/' + id)
        );
    }

    listWorkouts() {
        return this.get<WorkoutModel[]>(
            this.secureUrl()
        );
    }
}
