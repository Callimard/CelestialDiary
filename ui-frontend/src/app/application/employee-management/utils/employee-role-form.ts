import {FormControl, FormGroup} from "@angular/forms";
import {RoleManagementService} from "../../../../service/company-management/employee/role/role-management.service";
import {EmployeeDTO} from "../../../../data/company-management/employee/employee-dto";
import {RoleDTO} from "../../../../data/company-management/employee/role/role-dto";

export class EmployeeRoleForm extends FormGroup {

  private _allRoles: RoleDTO[] = [];

  constructor(private roleManagementService: RoleManagementService, employee?: EmployeeDTO) {
    super({});

    this.roleManagementService.allRoles().then((allRoles) => {
      this._allRoles = allRoles;
      this.createForm();
      this.mergeWithEmployee(employee);
    })
  }

  private createForm() {
    for (let role of this._allRoles) {
      this.addControl(role.id, new FormControl(false));
    }
  }

  private mergeWithEmployee(employee?: EmployeeDTO) {
    if (employee?.praticablePrestations != null) {
      for (let role of employee.roles) {
        this.setControl(role.id, new FormControl(true));
      }
    }
  }

  public selectedRoles(): string[] {
    let roles: string[] = [];
    const controlKeys = Object.keys(this.controls);
    const v = this.value;
    for (let roleId of controlKeys) {
      if (v[roleId] === true) {
        roles.push(roleId);
      }
    }

    return roles;
  }

  get allRoles(): RoleDTO[] {
    return this._allRoles;
  }
}
