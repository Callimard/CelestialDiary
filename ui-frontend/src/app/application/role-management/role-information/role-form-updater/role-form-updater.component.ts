import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {RoleDTO} from "../../../../../data/company-management/employee/role-dto";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CompanyManagementPrivilegeFormGroup} from "../../../../../service/company-management/company-management-privilege-form-group";
import {RoleUpdatedInformation} from "../../../../../data/company-management/employee/role-updated-information";
import {RoleManagementService} from "../../../../../service/company-management/role-management.service";

@Component({
  selector: '[app-role-form-updater]',
  templateUrl: './role-form-updater.component.html',
  styleUrls: ['./role-form-updater.component.css']
})
export class RoleFormUpdaterComponent implements OnInit, OnChanges {

  @Input() role!: RoleDTO;

  updateFailed = false;

  roleUpdateForm = new FormGroup({});

  constructor(private roleManagementService: RoleManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    this.roleUpdateForm = new FormGroup({
      name: new FormControl(null, [Validators.required]),
      description: new FormControl(null),
      companyManagementPrivileges: new CompanyManagementPrivilegeFormGroup()
    });

    this.roleUpdateForm.setControl('name', new FormControl(this.role.name));
    this.roleUpdateForm.setControl('description', new FormControl(this.role.description));

    for (let privilege of this.role.privileges) {
      if (privilege.identifierName === 'COMPANY_ALL') {
        this.companyManagementPrivilegesFormGroup.setControl('COMPANY_ALL', new FormControl(true));
      } else if (privilege.identifierName.startsWith('EMPLOYEE_')) {
        this.companyManagementPrivilegesFormGroup.employeeManagementFormGroup.setControl(privilege.identifierName, new FormControl(true));
      } else if (privilege.identifierName.startsWith('ESTABLISHMENT_')) {
        this.companyManagementPrivilegesFormGroup.establishmentManagementFormGroup.setControl(privilege.identifierName, new FormControl(true))
      } else if (privilege.identifierName.startsWith('SALEABLE_')) {
        this.companyManagementPrivilegesFormGroup.saleableManagementFormGroup.setControl(privilege.identifierName, new FormControl(true))
      } else if (privilege.identifierName.startsWith('ROLE_')) {
        this.companyManagementPrivilegesFormGroup.roleManagementFormGroup.setControl(privilege.identifierName, new FormControl(true))
      }
    }
  }

  onRoleUpdate() {
    const form = this.roleUpdateForm.value;
    let privileges: string[] = this.companyManagementPrivilegesFormGroup.extractPrivileges();
    let roleUpdates: RoleUpdatedInformation = {
      name: form.name,
      description: form.description,
      privilegeIdentifiers: privileges
    }

    this.roleManagementService.updateRole(this.role.id, roleUpdates).then((roleUpdated) => {
      this.role = roleUpdated;
      this.updateFailed = false;
    }).catch(() => {
      this.updateFailed = true;
    })
  }

  get companyManagementPrivilegesFormGroup(): CompanyManagementPrivilegeFormGroup {
    return this.roleUpdateForm.get('companyManagementPrivileges') as CompanyManagementPrivilegeFormGroup;
  }

}
