import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ScopeFormGroup} from "./scope-form-group";
import {ScopeDTO} from "../../../../data/security/privilege/scope-dto";
import {RoleDTO} from "../../../../data/company-management/person/employee/role/role-dto";

export class RoleFormGroup extends FormGroup {

  constructor(companyManagementScope: ScopeDTO, withValidators = false, role?: RoleDTO) {
    super({
      name: new FormControl(role?.name, withValidators ? [Validators.required] : []),
      description: new FormControl(role?.description),
      companyManagementScope: new ScopeFormGroup(companyManagementScope, role)
    });
  }

  get companyManagementScopeFormGroup(): ScopeFormGroup {
    return this.get('companyManagementScope') as ScopeFormGroup;
  }
}
