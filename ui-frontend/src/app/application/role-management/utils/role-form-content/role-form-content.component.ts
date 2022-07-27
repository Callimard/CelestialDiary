import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {PrivilegeDTO} from "../../../../../data/company-management/employee/privilege-dto";
import {PrivilegeManagementService} from "../../../../../service/company-management/employee/role/privilege/privilege-management.service";

@Component({
  selector: '[app-role-form-content]',
  templateUrl: './role-form-content.component.html',
  styleUrls: ['./role-form-content.component.css']
})
export class RoleFormContentComponent implements OnInit {

  @Input() roleFormGroup!: FormGroup;

  allCompanyPrivileges: PrivilegeDTO[] = [];

  allCompanyPrivilege?: PrivilegeDTO;
  companyEmployeePrivilege: PrivilegeDTO[] = [];
  companyEstablishmentPrivilege: PrivilegeDTO[] = [];
  companySaleablePrivilege: PrivilegeDTO[] = [];
  companyRolePrivilege: PrivilegeDTO[] = [];

  constructor(private privilegeManagementService: PrivilegeManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    this.chargeCompanyPrivilege();
  }

  private chargeCompanyPrivilege() {
    this.privilegeManagementService.allCompanyManagementPrivileges().then((allCompanyPrivileges) => {
      this.allCompanyPrivileges = allCompanyPrivileges;

      for (let privilege of this.allCompanyPrivileges) {
        const identifier = privilege.identifierName;

        if (identifier.startsWith('COMPANY_')) {
          this.allCompanyPrivilege = privilege;
        } else if (identifier.startsWith('EMPLOYEE_')) {
          this.addEmployeePrivilege(privilege);
        } else if (identifier.startsWith('ESTABLISHMENT_')) {
          this.addEstablishmentPrivilege(privilege);
        } else if (identifier.startsWith('SALEABLE_')) {
          this.addSaleablePrivilege(privilege);
        } else if (identifier.startsWith('ROLE_')) {
          this.addRolePrivilege(privilege);
        } else {
          console.error("Unknown company management privilege");
        }
      }
    });
  }

  private addEmployeePrivilege(privilege: PrivilegeDTO) {
    this.employeeManagementPrivilegeForm.addControl(privilege.identifierName, new FormControl(false));
    this.companyEmployeePrivilege.push(privilege);
  }

  private addEstablishmentPrivilege(privilege: PrivilegeDTO) {
    this.establishmentManagementPrivilegeForm.addControl(privilege.identifierName, new FormControl(false));
    this.companyEstablishmentPrivilege.push(privilege);
  }

  private addSaleablePrivilege(privilege: PrivilegeDTO) {
    this.saleableManagementPrivilegeForm.addControl(privilege.identifierName, new FormControl(false));
    this.companySaleablePrivilege.push(privilege);

  }

  private addRolePrivilege(privilege: PrivilegeDTO) {
    this.roleManagementPrivilegeForm.addControl(privilege.identifierName, new FormControl(false));
    this.companyRolePrivilege.push(privilege);
  }


  get companyManagementPrivilegesForm(): FormGroup {
    return this.roleFormGroup.get('companyManagementPrivileges') as FormGroup;
  }

  get employeeManagementPrivilegeForm(): FormGroup {
    return this.companyManagementPrivilegesForm.get('employeeManagement') as FormGroup;
  }

  get establishmentManagementPrivilegeForm(): FormGroup {
    return this.companyManagementPrivilegesForm.get('establishmentManagement') as FormGroup;
  }

  get saleableManagementPrivilegeForm(): FormGroup {
    return this.companyManagementPrivilegesForm.get('saleableManagement') as FormGroup;
  }

  get roleManagementPrivilegeForm(): FormGroup {
    return this.companyManagementPrivilegesForm.get('roleManagement') as FormGroup;
  }

}
