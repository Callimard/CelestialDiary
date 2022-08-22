import {Component, OnInit, ViewChild} from '@angular/core';
import {EstablishmentEquipmentSelectionComponent} from "../establishment-equipment-selection/establishment-equipment-selection.component";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-establishment-equipment-container',
  templateUrl: './establishment-equipment-container.component.html',
  styleUrls: ['./establishment-equipment-container.component.css']
})
export class EstablishmentEquipmentContainerComponent implements OnInit {

  @ViewChild("establishmentEquipmentSelection") establishmentSelection!: EstablishmentEquipmentSelectionComponent;

  establishmentId!: string;

  constructor(private activatedRoute: ActivatedRoute) {
    this.activatedRoute.params.subscribe(params => {
      this.establishmentId = params['establishmentId'];
    });
  }

  ngOnInit(): void {
    // Nothing
  }

}
