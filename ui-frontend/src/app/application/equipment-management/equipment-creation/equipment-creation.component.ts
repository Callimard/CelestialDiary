import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: '[app-equipment-creation]',
  templateUrl: './equipment-creation.component.html',
  styleUrls: ['./equipment-creation.component.css']
})
export class EquipmentCreationComponent implements OnInit {

  @Output() wantGoBack = new EventEmitter<boolean>();
  @Output() equipmentHasBeenCreated = new EventEmitter<boolean>();

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  goBack() {
    this.wantGoBack.emit(true);
  }
}
