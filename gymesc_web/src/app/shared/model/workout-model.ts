import { ExerciseModel } from "./exercise-model";

export class WorkoutModel {

    id!: number;

    name!: string;

    description!: string;

    duration!: number;

    exerciseList!: ExerciseModel[];

}
