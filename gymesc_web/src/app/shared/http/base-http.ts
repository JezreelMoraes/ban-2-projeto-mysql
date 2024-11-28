import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { EMPTY, Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { AuthService } from './auth.service';
import { ErrorResponse, GymEscHttpErrorResponse, InputFieldError } from './error-response';
import { environment } from "../../../environments/environment";

@Injectable({ providedIn: 'root' })
export abstract class BaseHttp {

    constructor(
        private httpClient: HttpClient,
        private authService: AuthService
    ) {
    }

    private addAuthHeader(headers?: HttpHeaders): HttpHeaders {
        const authToken = this.authService.getAuthToken();
        if (authToken) {
            headers = (headers || new HttpHeaders()).set('Authorization', `Bearer ${ authToken }`);
        }

        return headers || new HttpHeaders();
    }

    private handleSuccess<T>(response: T): T {
        return response;
    }

    private handleError(error: GymEscHttpErrorResponse): Observable<never> {
        if (!error.error) return EMPTY;

        console.error('ocorreu um erro:', error.error || error);

        const finalError = error.error as ErrorResponse || new ErrorResponse(
            new Date(),
            error.status,
            error.statusCode || undefined,
            error.statusText,
            error.message,
            error.url || "",
            error.fieldErrorList || []
        );

        if (finalError.fieldErrorList) {
            let errorMessage = finalError.message

            // @ts-ignore
            for (let inputError: InputFieldError of finalError.fieldErrorList) {
                errorMessage += "\n - " + inputError.message;
            }

            finalError.message = errorMessage;
        }

        if (finalError.statusCode === 500) {
            finalError.message = 'Ocorreu um erro inesperado. Tente novamente mais tarde.';
        }

        return throwError(() => finalError);
    }

    protected abstract getBasePath(): string

    protected publicUrl(path?: string): string {
        return environment.basePublicUrl + this.getBasePath() + (path || "")
    }

    protected secureUrl(path?: string): string {
        return environment.baseSecureUrl + this.getBasePath() + (path || "")
    }

    protected get<T>(url: string, headers?: HttpHeaders): Observable<T> {
        const updatedHeaders = this.addAuthHeader(headers);
        return this.httpClient
            .get<T>(url, { headers: updatedHeaders })
            .pipe(
                map((response: any) => this.handleSuccess(response)),
                catchError((error: GymEscHttpErrorResponse) => this.handleError(error))
            );
    }

    protected post<T>(url: string, body: any, headers?: HttpHeaders): Observable<T> {
        const updatedHeaders = this.addAuthHeader(headers);
        return this.httpClient
            .post<T>(url, body, { headers: updatedHeaders })
            .pipe(
                map((response: any) => this.handleSuccess(response)),
                catchError((error: GymEscHttpErrorResponse) => this.handleError(error))
            );
    }

    protected put<T>(url: string, body: any, headers?: HttpHeaders): Observable<T> {
        const updatedHeaders = this.addAuthHeader(headers);
        return this.httpClient
            .put<T>(url, body, { headers: updatedHeaders })
            .pipe(
                map((response: any) => this.handleSuccess(response)),
                catchError((error: GymEscHttpErrorResponse) => this.handleError(error))
            );
    }

    protected delete<T>(url: string, headers?: HttpHeaders): Observable<T> {
        const updatedHeaders = this.addAuthHeader(headers);
        return this.httpClient
            .delete<T>(url, { headers: updatedHeaders })
            .pipe(
                map((response: any) => this.handleSuccess(response)),
                catchError((error: GymEscHttpErrorResponse) => this.handleError(error))
            );
    }

//   constructor(private httpClient: HttpClient) {
//   }

//   private handleSuccess<T>(response: T): T {
//     return response;
//   }

//   private handleError(error: HttpErrorResponse): Observable<never> {
//     console.error('ocorreu um erro:', error.error || error);
//     return throwError(() => error.error || error);
//   }

//   get<T>(url: string, headers?: HttpHeaders): Observable<T> {
//     return this.httpClient
//       .get<T>(url, { headers })
//       .pipe(
//         map((response) => this.handleSuccess(response)),
//         catchError(this.handleError)
//       );
//   }

//   post<T>(url: string, body: any, headers?: HttpHeaders): Observable<T> {
//     return this.httpClient
//       .post<T>(url, body, { headers })
//       .pipe(
//         map((response) => this.handleSuccess(response)),
//         catchError(this.handleError)
//       );
//   }

//   put<T>(url: string, body: any, headers?: HttpHeaders): Observable<T> {
//     return this.httpClient
//       .put<T>(url, body, { headers })
//       .pipe(
//         map((response) => this.handleSuccess(response)),
//         catchError(this.handleError)
//       );
//   }

//   delete<T>(url: string, headers?: HttpHeaders): Observable<T> {
//     return this.httpClient
//       .delete<T>(url, { headers })
//       .pipe(
//         map((response) => this.handleSuccess(response)),
//         catchError(this.handleError)
//       );
//   }
}
