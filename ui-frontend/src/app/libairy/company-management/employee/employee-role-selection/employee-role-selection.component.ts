import {Component, Input, OnInit} from '@angular/core';
import {RoleFormGroup} from "../../../../../service/company-management/role-form-group";
import {RoleManagementService} from "../../../../../service/company-management/role-management.service";
import {FormControl} from "@angular/forms";
import {RoleDTO} from "../../../../../data/company-management/employee/role-dto";

@Component({
  selector: '[app-employee-role-selection]',
  templateUrl: './employee-role-selection.component.html',
  styleUrls: ['./employee-role-selection.component.css']
})
export class EmployeeRoleSelectionComponent implements OnInit {

  @Input() roleFormGroup!: RoleFormGroup;

  allRoles: RoleDTO[] = [];

  constructor(private roleManagementService: RoleManagementService) {
    // Nothing
  }

  ngOnInit(): void {
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
