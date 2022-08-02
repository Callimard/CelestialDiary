import {FormGroup} from "@angular/forms";

export class EmployeeRoleForm extends FormGroup{

  constructor() {
    super({});
  }

  public extractRoles(): string[] {
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
}
