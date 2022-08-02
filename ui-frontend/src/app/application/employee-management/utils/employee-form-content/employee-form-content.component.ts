import {Component, Input, OnInit} from '@angular/core';
import {EmployeeForm} from "../employee-form";

@Component({
  selector: '[app-employee-form-content]',
  templateUrl: './employee-form-content.component.html',
  styleUrls: ['./employee-form-content.component.css']
})
export class EmployeeFormContentComponent implements OnInit {

  @Input() employeeFormGroup!: EmployeeForm
  @Input() displayRoleSelection = false;

  constructor() {
    // Noting
  }

  ngOnInit(): void {
    // Noting
  }

}
