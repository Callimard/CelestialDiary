import {FormControl, FormGroup} from "@angular/forms";
import {EmployeeRoleForm} from "./employee-role-form";
import {EmployeeDTO} from "../../../../data/company-management/employee/employee-dto";
import {EmployeePrestationForm} from "./employee-prestation-form";
import {PrestationManagementService} from "../../../../service/company-management/saleable/prestation-management.service";

export class EmployeeForm extends FormGroup {

  constructor(private prestationManagementService: PrestationManagementService, employee?: EmployeeDTO) {
    super({
      email: new FormControl(employee?.email),
      password: new FormControl(employee != null ? "************" : null),
      firstName: new FormControl(employee?.firstName),
      lastName: new FormControl(employee?.lastName),
      comment: new FormControl(employee?.comment),
      gender: new FormControl(employee?.gender),
      phone: new FormControl(employee?.phone),
      tags: new FormControl(employee != null ? EmployeeForm.extractEmployeeTags(employee) : null),
      praticablePrestations: new EmployeePrestationForm(prestationManagementService),
      roles: new EmployeeRoleForm()
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
