import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {EmployeeDTO} from "../../../../../data/company-management/employee/employee-dto";
import {EmployeeManagementService} from "../../../../../service/company-management/employee/employee-management.service";
import {EmployeeUpdatedRoles} from "../../../../../data/company-management/employee/employee-updated-roles";
import {FormControl} from "@angular/forms";
import {EmployeeRoleForm} from "../../utils/employee-role-form";

@Component({
  selector: '[app-employee-role-updater]',
  templateUrl: './employee-role-updater.component.html',
  styleUrls: ['./employee-role-updater.component.css']
})
export class EmployeeRoleUpdaterComponent implements OnInit, OnChanges {

  updateFailed = false;

  @Input() employee!: EmployeeDTO

  employeeRoleFormGroup = new EmployeeRoleForm();

  constructor(private employeeManagementService: EmployeeManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    this.employeeRoleFormGroup = new EmployeeRoleForm();
    this.mergeEmployeeRole();
  }

  onEmployeeUpdateRole() {
    const employeeRoleUpdates: EmployeeUpdatedRoles = {
      roles: this.employeeRoleFormGroup.extractRoles()
    }
    this.employeeManagementService.updateEmployeeRoles(this.employee.id, employeeRoleUpdates).then((employee) => {
      this.updateFailed = false;
      this.employee = employee;
      this.mergeEmployeeRole();
    }).catch(() => {
      this.updateFailed = true;
    })
  }

  private mergeEmployeeRole() {
    for (let role of this.employee.roles) {
      this.employeeRoleFormGroup.addControl(role.id, new FormControl(true));
    }
  }

}
