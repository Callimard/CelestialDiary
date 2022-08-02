import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {PrivilegeManagementService} from "../../../../../service/security/privilege/privilege-management.service";
import {RoleCreationInformation} from "../../../../../data/company-management/employee/role/role-creation-information";
import {RoleManagementService} from "../../../../../service/company-management/employee/role/role-management.service";
import {RoleFormGroup} from "../../utils/role-form-group";

@Component({
  selector: '[app-role-form-creator]',
  templateUrl: './role-form-creator.component.html',
  styleUrls: ['./role-form-creator.component.css']
})
export class RoleFormCreatorComponent implements OnInit {

  creationFailed = false;

  @Output() roleCreated = new EventEmitter<boolean>();

  roleCreationForm!: RoleFormGroup;

  constructor(private privilegeManagementService: PrivilegeManagementService, private roleManagementService: RoleManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    this.initRoleCreationForm();
  }

  private initRoleCreationForm() {
    this.privilegeManagementService.companyManagementScope().then((companyManagementScope) => {
      this.roleCreationForm = new RoleFormGroup(companyManagementScope, true);
    })
  }

  onRoleCreation() {
    const form = this.roleCreationForm.value;
    let companyManagementPrivilegeIdentifiers: string[] = this.roleCreationForm.companyManagementScopeFormGroup.getSelectedPrivileges();
    let roleInformation: RoleCreationInformation = {
      name: form.name,
      description: form.description,
      companyPrivilegeIdentifiers: companyManagementPrivilegeIdentifiers
    }

    console.log(roleInformation);

    this.roleManagementService.createRole(roleInformation).then(() => {
      this.roleCreated.emit(true);
      this.creationFailed = false;
    }).catch(() => {
      this.creationFailed = true;
    })
  }
}
