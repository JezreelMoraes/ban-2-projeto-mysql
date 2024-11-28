import { Component } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from "@angular/router";
import { AuthService } from '../../shared/http/auth.service';
import { ButtonComponent } from "../button/button.component";
import { ContainerComponent } from "../container/container.component";
import { LogoComponent } from "../logo/logo.component";

@Component({
  selector: 'app-sidebar',
  standalone: true,
    imports: [
        ContainerComponent,
        ButtonComponent,
        RouterLink,
        RouterLinkActive,
        LogoComponent
    ],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.scss'
})
export class SidebarComponent {

    constructor(private authService: AuthService, private router: Router) {}

    logout() {
        this.authService.removeAuthToken();
        this.router.navigateByUrl("/login");
    }
}
