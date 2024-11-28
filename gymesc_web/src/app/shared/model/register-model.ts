import { WorkoutLevelEnum } from "../enum/workout-level.enum";

export class RegisterModel {

    email!: string;

    password!: string;

    rePassword!: string;

    name!: string;

    weight!: number;

    height!: number;

    workoutLevelEnum!: WorkoutLevelEnum;

}
