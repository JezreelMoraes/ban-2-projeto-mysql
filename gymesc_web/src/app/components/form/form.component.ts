import { Component, Input } from '@angular/core';

@Component({
    selector: 'app-form',
    standalone: true,
    imports: [],
    templateUrl: './form.component.html',
    styleUrl: './form.component.scss'
})
export class FormComponent {
    @Input() method: "POST" | "GET" = "POST"
    @Input() action: string = "#"
}
