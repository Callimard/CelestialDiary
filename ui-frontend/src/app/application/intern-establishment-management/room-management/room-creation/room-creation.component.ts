import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: '[app-room-creation]',
  templateUrl: './room-creation.component.html',
  styleUrls: ['./room-creation.component.css']
})
export class RoomCreationComponent implements OnInit {

  @Input() establishmentId!: string;

  @Output() roomCreated = new EventEmitter();
  @Output() wantGoBack = new EventEmitter();

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

}
