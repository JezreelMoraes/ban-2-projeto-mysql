import {Component, Input} from '@angular/core';
import { NgOptimizedImage } from "@angular/common";

@Component({
  selector: 'app-illustration',
  standalone: true,
    imports: [
        NgOptimizedImage
    ],
  templateUrl: './illustration.component.html',
  styleUrl: './illustration.component.scss'
})
export class IllustrationComponent {
    @Input() title: string = ""
    @Input() illustration: string = ""
    @Input() illustrationExtension: string = "svg"
    @Input() alt: string = ""
    @Input() description: string = ""
}
