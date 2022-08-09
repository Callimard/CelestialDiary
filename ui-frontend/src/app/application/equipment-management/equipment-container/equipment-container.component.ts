import {Component, OnInit, ViewChild} from '@angular/core';
import {EquipmentSelectionComponent} from "../equipment-selection/equipment-selection.component";

@Component({
  selector: 'app-equipment-container',
  templateUrl: './equipment-container.component.html',
  styleUrls: ['./equipment-container.component.css']
})
export class EquipmentContainerComponent implements OnInit {

  @ViewChild('equipmentSelection') equipmentSelection!: EquipmentSelectionComponent;

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

}
