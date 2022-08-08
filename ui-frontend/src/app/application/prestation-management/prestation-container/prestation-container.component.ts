import {Component, OnInit, ViewChild} from '@angular/core';
import {PrestationSelectionComponent} from "../prestation-selection/prestation-selection.component";

@Component({
  selector: 'app-prestation-container',
  templateUrl: './prestation-container.component.html',
  styleUrls: ['./prestation-container.component.css']
})
export class PrestationContainerComponent implements OnInit {

  @ViewChild('prestationSelection') prestationSelection!: PrestationSelectionComponent;

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

}
