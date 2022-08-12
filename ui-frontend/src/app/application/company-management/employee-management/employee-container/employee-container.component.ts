import {Component, OnInit, ViewChild} from '@angular/core';
import {EmployeeSelectionComponent} from "../employee-selection/employee-selection.component";

@Component({
  selector: 'app-employee-container',
  templateUrl: './employee-container.component.html',
  styleUrls: ['./employee-container.component.css', '../../../../library/container/two-side-management/two-side-management.component.css']
})
export class EmployeeContainerComponent implements OnInit {

  @ViewChild('employeeSelection') employeeSelection!: EmployeeSelectionComponent;

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }
}
