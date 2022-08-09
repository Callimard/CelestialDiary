import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {PrivilegeManagementService} from "../../../../../service/security/privilege/privilege-management.service";
import {RoleCreationInformation} from "../../../../../data/company-management/person/employee/role/role-creation-information";
import {RoleManagementService} from "../../../../../service/company-management/employee/role/role-management.service";
import {RoleFormGroup} from "../../utils/role-form-group";
import {EstablishmentManagementService} from "../../../../../service/company-management/establishment/establishment-management.service";

@Component({
  selector: '[app-role-form-creator]',
  templateUrl: './role-form-creator.component.html',
  styleUrls: ['./role-form-creator.component.css']
})
export class RoleFormCreatorComponent implements OnInit {

  creationFailed = false;

  @Output() roleCreated = new EventEmitter<boolean>();

  roleCreationForm!: RoleFormGroup;

  constructor(private privilegeManagementService: PrivilegeManagementService,
              private establishmentManagementService: EstablishmentManagementService,
              private roleManagementService: RoleManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    this.initRoleCreationForm();
  }

  private initRoleCreationForm() {
    this.roleCreationForm = new RoleFormGroup(this.privilegeManagementService, this.establishmentManagementService, true);
  }

  onRoleCreation() {
    let roleInformation: RoleCreationInformation = this.roleCreationForm.extractRoleInformation() as RoleCreationInformation;

    this.roleManagementService.createRole(roleInformation).then(() => {
      this.roleCreated.emit(true);
      this.creationFailed = false;
    }).catch(() => {
      this.creationFailed = true;
    })
  }
}
