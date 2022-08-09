import {Component, OnInit, ViewChild} from '@angular/core';
import {ClientSelectionComponent} from "../client-selection/client-selection.component";

@Component({
  selector: 'app-client-container',
  templateUrl: './client-container.component.html',
  styleUrls: ['./client-container.component.css']
})
export class ClientContainerComponent implements OnInit {

  @ViewChild('clientSelection') clientSelection!: ClientSelectionComponent;

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

}
