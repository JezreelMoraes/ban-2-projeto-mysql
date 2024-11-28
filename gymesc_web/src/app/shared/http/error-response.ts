import { HttpErrorResponse } from "@angular/common/http";

export class GymEscHttpErrorResponse  extends HttpErrorResponse {

    fieldErrorList: InputFieldError[]
    statusCode: number | null;

    constructor(props: any) {
        super(props);
        this.fieldErrorList = props.fieldErrorList
        this.statusCode = props.statusCode
    }

}

export class ErrorResponse {

    timestamp: Date | null;

    status: number | null;

    statusCode: number | null;

    error: string;

    message: string;

    path: string;

    fieldErrorList: InputFieldError[]

    constructor(timestamp?: Date, status?: number, statusCode?: number, error?: string, message?: string, path?: string, fieldErrorList?: InputFieldError[]) {
        this.timestamp = timestamp || new Date();
        this.status = status || null;
        this.statusCode = statusCode || null;
        this.error = error || '';
        this.message = message || "";
        this.path = path || "";
        this.fieldErrorList = fieldErrorList || []
    }
}

export class InputFieldError {

    public field: string

    public message: string

    constructor(field: string, message: string) {
        this.field = field
        this.message = message
    }

}
