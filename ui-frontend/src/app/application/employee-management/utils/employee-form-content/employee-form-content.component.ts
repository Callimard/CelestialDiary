import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {EmployeeRoleForm} from "../employee-role-form";

@Component({
  selector: '[app-employee-form-content]',
  templateUrl: './employee-form-content.component.html',
  styleUrls: ['./employee-form-content.component.css']
})
export class EmployeeFormContentComponent implements OnInit {

  @Input() employeeFormGroup!: FormGroup
  @Input() displayRoleSelection = false;

  constructor() {
    // Noting
  }

  ngOnInit(): void {
    // Noting
  }

  get roleFormGroup(): EmployeeRoleForm {
    return this.employeeFormGroup.get('roles') as EmployeeRoleForm;
  }

}
