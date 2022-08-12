import {Component, Input, OnInit} from '@angular/core';
import {RoleDTO} from "../../../../../../data/model/person/employee/role/role-dto";
import {EmployeeRoleForm} from "../employee-role-form";

@Component({
  selector: '[app-employee-role-selection]',
  templateUrl: './employee-role-selection.component.html',
  styleUrls: ['./employee-role-selection.component.css']
})
export class EmployeeRoleSelectionComponent implements OnInit {

  @Input() roleFormGroup!: EmployeeRoleForm;

  allRoles: RoleDTO[] = [];

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

}
