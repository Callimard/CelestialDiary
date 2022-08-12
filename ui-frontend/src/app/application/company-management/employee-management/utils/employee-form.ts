import {FormControl, FormGroup, Validators} from "@angular/forms";
import {EmployeeRoleForm} from "./employee-role-form";
import {EmployeeDTO} from "../../../../../data/model/person/employee/employee-dto";
import {EmployeePrestationForm} from "./employee-prestation-form";
import {PrestationManagementService} from "../../../../../service/company-management/saleable/prestation-management.service";
import {RoleManagementService} from "../../../../../service/company-management/employee/role/role-management.service";

export class EmployeeForm extends FormGroup {

  public static readonly DEFAULT_PASSWORD: string = "***************";

  constructor(private prestationManagementService: PrestationManagementService, private roleManagementService: RoleManagementService, withValidators: boolean = false, employee?: EmployeeDTO) {
    super({
      email: new FormControl(employee?.email, withValidators ? [Validators.required] : []),
      password: new FormControl(employee != null ? EmployeeForm.DEFAULT_PASSWORD : null, withValidators ? [Validators.required] : []),
      firstName: new FormControl(employee?.firstName, withValidators ? [Validators.required] : []),
      lastName: new FormControl(employee?.lastName, withValidators ? [Validators.required] : []),
      comment: new FormControl(employee?.comment),
      gender: new FormControl(employee?.gender),
      phone: new FormControl(employee?.phone),
      tags: new FormControl(employee != null ? EmployeeForm.extractEmployeeTags(employee) : null),
      praticablePrestations: new EmployeePrestationForm(prestationManagementService, employee),
      roles: new EmployeeRoleForm(roleManagementService, employee)
    });
  }

  private static extractEmployeeTags(employee: EmployeeDTO): string {
    let allTagsConcat: string = "";
    for (let tag of employee.tags) {
      allTagsConcat += tag + " ";
    }
    return allTagsConcat;
  }

  get prestationFormGroup(): EmployeePrestationForm {
    return this.get('praticablePrestations') as EmployeePrestationForm;
  }

  get roleFormGroup(): EmployeeRoleForm {
    return this.get('roles') as EmployeeRoleForm;
  }
}
