import { isPlatformBrowser } from '@angular/common';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class AuthService {

    private storage: Storage | null = null;
    private isBrowser: boolean;

    constructor(@Inject(PLATFORM_ID) private platformId: object) {
        this.isBrowser = isPlatformBrowser(platformId);

        // Inicializa localStorage apenas se estiver no navegador
        if (this.isBrowser) {
            this.storage = window.localStorage;
        }
    }

    getAuthToken(): string {
        if (!this.isBrowser || !this.storage) {
            return "";
        }

        return this.storage.getItem("auth") as string;
    }

    setAuthToken(authToken: string): void {
        if (this.isBrowser && this.storage) {
            this.storage.setItem("auth", authToken);
        }
    }

    removeAuthToken(): void {
        if (this.isBrowser && this.storage) {
            this.storage.removeItem("auth");
        }
    }

    isLoggedIn(): boolean {
        return !!this.getAuthToken();
    }
}
