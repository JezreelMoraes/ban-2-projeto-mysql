export interface LoginResponse {
    jwt: string;
    userDTO: {
        id: number;
        active: boolean;
        email: string;
        name: string;
        phoneNumber: string | null;
        federalIdentification: string | null;
        weight: number;
        height: number;
        workoutLevelEnum: string;
    };
}
