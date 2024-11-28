import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { EMPTY, Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AuthService } from "./auth.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(private authService: AuthService, private router: Router) {}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const authToken = this.authService.getAuthToken();

        let authReq = req;

        if (authToken) {
            authReq = req.clone({
                setHeaders: {
                    Authorization: `Bearer ${authToken}`
                }
            });
        }

        return next.handle(authReq).pipe(
            catchError((error) => {
                if (error.status === 401) {
                    this.authService.removeAuthToken()
                    this.router.navigate(['/login']);
                }

                return EMPTY;
            })
        );
    }
}
