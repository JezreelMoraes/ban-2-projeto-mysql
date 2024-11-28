import { ExerciseTypeEnum } from "../enum/exercise-type.enum";
import { CategoryModel } from "./category-model";

export class ExerciseModel {

    id!: number;

    name!: string;

    description!: string;

    exerciseTypeEnum!: ExerciseTypeEnum;

    category!: CategoryModel;

}
