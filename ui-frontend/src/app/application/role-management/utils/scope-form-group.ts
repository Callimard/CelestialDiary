import {FormControl, FormGroup} from "@angular/forms";
import {ScopeDTO} from "../../../../data/security/privilege/scope-dto";
import {PrivilegeDTO} from "../../../../data/security/privilege/privilege-dto";
import {RoleDTO} from "../../../../data/company-management/person/employee/role/role-dto";

export class ScopeFormGroup extends FormGroup {

  public readonly _scopeName: string;
  public readonly _scopeDescription: string;
  public readonly _scopeChildren: ScopeFormGroup[] = [];
  public readonly _scopePrivileges: PrivilegeDTO[] = [];

  constructor(scope: ScopeDTO, role?: RoleDTO) {
    super({});

    this._scopeName = scope.name;
    this._scopeDescription = scope.description;

    this._scopePrivileges = scope.privileges;

    this.createPrivilegesFormControl();
    this.createScopeFormChildren(scope, role);
    this.mergeWithSpecifiedRole(role);
  }

  private createPrivilegesFormControl() {
    for (let privilege of this._scopePrivileges) {
      this.addControl(privilege.identifierName, new FormControl(false));
    }
  }

  private createScopeFormChildren(scope: ScopeDTO, role: RoleDTO | undefined) {
    for (let scopeChild of scope.scopeChildren) {
      const scopeForm = new ScopeFormGroup(scopeChild, role);
      this._scopeChildren.push(scopeForm);
      this.addControl(scope.name, scopeForm);
    }
  }

  private mergeWithSpecifiedRole(role: RoleDTO | undefined) {
    if (role != null) {
      for (let companyPrivilege of role.companyPrivileges) {
        if (this.get(companyPrivilege.identifierName) != null)
          this.setControl(companyPrivilege.identifierName, new FormControl(true));
      }

      for (let establishmentRole of role.establishmentRoles) {
        for (let establishmentPrivilege of establishmentRole.establishmentPrivileges) {
          if (this.get(establishmentPrivilege.identifierName) != null)
            this.setControl(establishmentPrivilege.identifierName, new FormControl(true))
        }
      }
    }
  }

  public getSelectedPrivileges(): string[] {
    let privileges: string[] = [];

    const keys: string[] = Object.keys(this.controls);
    for (let key of keys) {
      const v = this.get(key)?.value;
      if (v) {
        privileges.push(key);
      }
    }

    for (let scopeChild of this._scopeChildren) {
      const selectedPrivileges: string[] = scopeChild.getSelectedPrivileges();
      privileges = privileges.concat(selectedPrivileges);
    }

    return privileges;
  }

  get scopeChildren(): ScopeFormGroup[] {
    return this._scopeChildren
  }

  getScopeChildFormGroup(name: string): ScopeFormGroup {
    return this.get(name) as ScopeFormGroup;
  }
}
