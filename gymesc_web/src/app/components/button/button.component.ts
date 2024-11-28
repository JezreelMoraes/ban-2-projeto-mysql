import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-button',
  standalone: true,
    imports: [],
  templateUrl: './button.component.html',
  styleUrl: './button.component.scss'
})
export class ButtonComponent {
    @Input() redirectUrl: string | null = null
    @Input() iconName: string | null = null
    @Input() label: string | null = null
    @Input() iconAlt: string = ""
}
