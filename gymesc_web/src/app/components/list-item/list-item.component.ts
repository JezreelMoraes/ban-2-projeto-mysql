import { Component, Input, Output, EventEmitter } from '@angular/core';
import { NgIf } from "@angular/common";
import { MatIcon } from "@angular/material/icon";

@Component({
  selector: 'app-list-item',
  standalone: true,
  imports: [
    NgIf,
    MatIcon
  ],
  templateUrl: './list-item.component.html',
  styleUrls: ['./list-item.component.scss']
})
export class ListItemComponent {
  @Input() imageUrl!: string;
  @Input() title!: string;
  @Input() description!: string;

  @Output() onEdit = new EventEmitter<void>();
  @Output() onDelete = new EventEmitter<void>();
}
