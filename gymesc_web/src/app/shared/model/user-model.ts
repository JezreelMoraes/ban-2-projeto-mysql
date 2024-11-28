import { WorkoutLevelEnum } from "../enum/workout-level.enum";

export class UserModel {

    id!: number;

    active!: boolean;

    email!: string;

    name!: string;

    phoneNumber!: string;

    federalIdentification!: string | null;

    weight!: number;

    height!: number;

    workoutLevelEnum!: WorkoutLevelEnum;

}
