import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';

import { HTTP_INTERCEPTORS, provideHttpClient, withFetch, withInterceptorsFromDi } from '@angular/common/http';
import { provideClientHydration } from '@angular/platform-browser';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { AuthInterceptor } from './shared/http/auth.interceptor';
import { AuthService } from './shared/http/auth.service';
import { routes } from './app.routes';

export const appConfig: ApplicationConfig = {
    providers: [
        provideHttpClient(withFetch()),
        AuthService,
        provideRouter(routes),
        provideClientHydration(),
        provideAnimationsAsync(),
        { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
        provideAnimationsAsync()
    ]
};
