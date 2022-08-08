import {Component, OnInit, ViewChild} from '@angular/core';
import {EstablishmentSelectionComponent} from "../establishment-selection/establishment-selection.component";

@Component({
  selector: 'app-establishment-container',
  templateUrl: './establishment-container.component.html',
  styleUrls: ['./establishment-container.component.css']
})
export class EstablishmentContainerComponent implements OnInit {

  @ViewChild('establishmentSelection') establishmentSelection!: EstablishmentSelectionComponent;

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

}
