import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {RoleManagementService} from "../../../../../service/company-management/employee/role/role-management.service";
import {FormControl} from "@angular/forms";
import {RoleDTO} from "../../../../../data/company-management/employee/role/role-dto";
import {EmployeeRoleForm} from "../employee-role-form";

@Component({
  selector: '[app-employee-role-selection]',
  templateUrl: './employee-role-selection.component.html',
  styleUrls: ['./employee-role-selection.component.css']
})
export class EmployeeRoleSelectionComponent implements OnInit, OnChanges {

  @Input() roleFormGroup!: EmployeeRoleForm;

  allRoles: RoleDTO[] = [];

  constructor(private roleManagementService: RoleManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    this.chargeAllRoles();
  }

  private chargeAllRoles() {
    this.roleManagementService.allRoles().then((allRoles) => {
      this.allRoles = allRoles;
      for (let role of this.allRoles) {
        this.roleFormGroup.addControl(role.id, new FormControl(false));
      }
    });
  }

}
