import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: '[app-establishment-equipment-adding]',
  templateUrl: './establishment-equipment-adding.component.html',
  styleUrls: ['./establishment-equipment-adding.component.css']
})
export class EstablishmentEquipmentAddingComponent implements OnInit {

  @Input() establishmentId!: string

  @Output() wantGoBack = new EventEmitter<boolean>();
  @Output() establishmentEquipmentHasBeenAdded = new EventEmitter<boolean>();

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

}
