import {FormControl, FormGroup} from "@angular/forms";
import {ScopeDTO} from "../../../../data/security/privilege/scope-dto";
import {PrivilegeDTO} from "../../../../data/security/privilege/privilege-dto";

export class ScopeFormGroup extends FormGroup {

  public readonly _scopeName: string;
  public readonly _scopeDescription: string;
  public readonly _scopeChildren: ScopeFormGroup[] = [];
  public readonly _scopePrivileges: PrivilegeDTO[] = [];

  constructor(scope: ScopeDTO, checkedPrivileges?: PrivilegeDTO[]) {
    super({});

    this._scopeName = scope.name;
    this._scopeDescription = scope.description;

    this._scopePrivileges = scope.privileges;

    this.createPrivilegesFormControl();
    this.createScopeFormChildren(scope, checkedPrivileges);

    if (checkedPrivileges != null)
      this.mergeWithSpecifiedRole(checkedPrivileges);
  }

  private createPrivilegesFormControl() {
    for (let privilege of this._scopePrivileges) {
      this.addControl(privilege.identifierName, new FormControl(false));
    }
  }

  private createScopeFormChildren(scope: ScopeDTO, checkedPrivileges?: PrivilegeDTO[]) {
    for (let scopeChild of scope.scopeChildren) {
      const scopeForm = new ScopeFormGroup(scopeChild, checkedPrivileges);
      this._scopeChildren.push(scopeForm);
      this.addControl(scope.name, scopeForm);
    }
  }

  private mergeWithSpecifiedRole(checkedPrivileges: PrivilegeDTO[]) {
    for (let privilege of checkedPrivileges) {
      if (this.get(privilege.identifierName) != null) {
        this.setControl(privilege.identifierName, new FormControl(true));
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

  get scopeName(): string {
    return this._scopeName;
  }

  get scopeDescription(): string {
    return this._scopeDescription;
  }

  get scopePrivileges(): PrivilegeDTO[] {
    return this._scopePrivileges;
  }

  get scopeChildren(): ScopeFormGroup[] {
    return this._scopeChildren;
  }

  getScopeChildFormGroup(name: string): ScopeFormGroup {
    return this.get(name) as ScopeFormGroup;
  }
}
