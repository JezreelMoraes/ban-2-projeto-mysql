export class CategoryModel {

    id!: number;

    name!: string;

    constructor(name: string) {
        this.name = name;
    }

    public static buildDefault(name: string): CategoryModel {
        return new CategoryModel(name)
    }

}
