import { Component } from '@angular/core';
import { Router, RouterOutlet } from "@angular/router";
import { AuthService } from '../../../shared/http/auth.service';
import { SidebarComponent } from "../../sidebar/sidebar.component";
import { ContainerComponent } from "../../container/container.component";

@Component({
  selector: 'app-application-layout',
  standalone: true,
    imports: [
        RouterOutlet,
        SidebarComponent,
        ContainerComponent
    ],
  templateUrl: './application-layout.component.html',
  styleUrl: './application-layout.component.scss'
})
export class ApplicationLayoutComponent {

    constructor(private authService: AuthService, private router: Router) {}

    ngOnInit() {
        if (!this.authService.isLoggedIn()) {
            this.router.navigateByUrl("/login")
        }
    }

}
