import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {PrivilegeManagementService} from "../../../../../service/company-management/employee/role/privilege/privilege-management.service";
import {RoleCreationInformation} from "../../../../../data/company-management/employee/role/role-creation-information";
import {CompanyPrivilegeFormGroup} from "../../../../../service/company-management/employee/role/privilege/company-privilege-form-group";
import {RoleManagementService} from "../../../../../service/company-management/employee/role/role-management.service";

@Component({
  selector: '[app-role-form-creator]',
  templateUrl: './role-form-creator.component.html',
  styleUrls: ['./role-form-creator.component.css']
})
export class RoleFormCreatorComponent implements OnInit {

  creationFailed = false;

  @Output() roleCreated = new EventEmitter<boolean>();

  roleCreationForm = new FormGroup({
    name: new FormControl(null, [Validators.required]),
    description: new FormControl(null),
    companyManagementPrivileges: new CompanyPrivilegeFormGroup()
  });

  constructor(private privilegeManagementService: PrivilegeManagementService, private roleManagementService: RoleManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  onRoleCreation() {
    const form = this.roleCreationForm.value;
    let companyManagementPrivilegeIdentifiers: string[] = this.companyManagementPrivilegesFormGroup.extractPrivileges();
    let roleInformation: RoleCreationInformation = {
      name: form.name,
      description: form.description,
      companyPrivilegeIdentifiers: companyManagementPrivilegeIdentifiers
    }

    this.roleManagementService.createRole(roleInformation).then(() => {
      this.roleCreated.emit(true);
      this.creationFailed = false;
    }).catch(() => {
      this.creationFailed = true;
    })
  }

  get companyManagementPrivilegesFormGroup(): CompanyPrivilegeFormGroup {
    return this.roleCreationForm.get('companyManagementPrivileges') as CompanyPrivilegeFormGroup;
  }
}
