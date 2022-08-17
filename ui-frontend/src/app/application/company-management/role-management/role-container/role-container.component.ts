import {Component, OnInit, ViewChild} from '@angular/core';
import {RoleSelectionComponent} from "../role-selection/role-selection.component";

@Component({
  selector: 'app-role-container',
  templateUrl: './role-container.component.html',
  styleUrls: ['./role-container.component.css']
})
export class RoleContainerComponent implements OnInit {

  @ViewChild('roleSelection') roleSelection!: RoleSelectionComponent;

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

}
