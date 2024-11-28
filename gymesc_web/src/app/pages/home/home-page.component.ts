import { Component } from '@angular/core';
import { RouterOutlet } from "@angular/router";
import { SidebarComponent } from "../../components/sidebar/sidebar.component";
import {BreadcrumbComponent} from "../../components/breadcrumb/breadcrumb.component";
import { IllustrationComponent } from "../../components/illustration/illustration.component";
import { ContainerComponent } from "../../components/container/container.component";
import { BreadcrumbItemComponent } from "../../components/breadcrumb-item/breadcrumb-item.component";

@Component({
    selector: 'app-home-page',
    standalone: true,
    imports: [
        SidebarComponent,
        RouterOutlet,
        BreadcrumbComponent,
        IllustrationComponent,
        ContainerComponent,
        BreadcrumbItemComponent,
    ],
    templateUrl: './home-page.component.html',
    styleUrl: './home-page.component.scss'
})
export class HomePageComponent {

}
