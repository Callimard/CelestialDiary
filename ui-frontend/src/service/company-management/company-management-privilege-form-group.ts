import {FormControl, FormGroup} from "@angular/forms";
import {PrivilegeFormGroup} from "./privilege-form-group";

export class CompanyManagementPrivilegeFormGroup extends FormGroup {
  constructor() {
    super({
      COMPANY_ALL: new FormControl(false),
      employeeManagement: new PrivilegeFormGroup(),
      establishmentManagement: new PrivilegeFormGroup(),
      saleableManagement: new PrivilegeFormGroup(),
      roleManagement: new PrivilegeFormGroup(),
    });
  }

  public extractPrivileges(): string[] {
    let privileges: string[] = [];

    if (this.value.COMPANY_ALL === true) {
      privileges.push('COMPANY_ALL');
      return privileges;
    }

    const employeePrivileges: string[] = this.employeeManagementFormGroup.extractPrivileges();
    const establishmentPrivileges: string[] = this.establishmentManagementFormGroup.extractPrivileges();
    const saleablePrivileges: string[] = this.saleableManagementFormGroup.extractPrivileges();
    const rolePrivileges: string[] = this.roleManagementFormGroup.extractPrivileges();

    privileges = privileges.concat(employeePrivileges);
    privileges = privileges.concat(establishmentPrivileges);
    privileges = privileges.concat(saleablePrivileges);
    privileges = privileges.concat(rolePrivileges);

    return privileges;
  }

  public get employeeManagementFormGroup(): PrivilegeFormGroup {
    return (this.get('employeeManagement') as PrivilegeFormGroup)
  }

  public get establishmentManagementFormGroup(): PrivilegeFormGroup {
    return (this.get('establishmentManagement') as PrivilegeFormGroup)
  }

  public get saleableManagementFormGroup(): PrivilegeFormGroup {
    return (this.get('saleableManagement') as PrivilegeFormGroup)
  }

  public get roleManagementFormGroup(): PrivilegeFormGroup {
    return (this.get('roleManagement') as PrivilegeFormGroup)
  }
}
