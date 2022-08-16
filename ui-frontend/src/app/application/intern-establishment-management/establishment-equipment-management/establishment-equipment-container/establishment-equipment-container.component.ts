import {Component, OnInit, ViewChild} from '@angular/core';
import {EstablishmentEquipmentSelectionComponent} from "../establishment-equipment-selection/establishment-equipment-selection.component";

@Component({
  selector: 'app-establishment-equipment-container',
  templateUrl: './establishment-equipment-container.component.html',
  styleUrls: ['./establishment-equipment-container.component.css']
})
export class EstablishmentEquipmentContainerComponent implements OnInit {

  @ViewChild("establishmentEquipmentSelection") establishmentSelection!: EstablishmentEquipmentSelectionComponent;

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

}
