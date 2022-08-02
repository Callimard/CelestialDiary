import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {RoleDTO} from "../../../../../data/company-management/employee/role/role-dto";
import {RoleUpdatedInformation} from "../../../../../data/company-management/employee/role/role-updated-information";
import {RoleManagementService} from "../../../../../service/company-management/employee/role/role-management.service";
import {PrivilegeService} from "../../../../../service/authentication/privilege.service";
import {RoleFormGroup} from "../../utils/role-form-group";
import {PrivilegeManagementService} from "../../../../../service/security/privilege/privilege-management.service";

@Component({
  selector: '[app-role-form-updater]',
  templateUrl: './role-form-updater.component.html',
  styleUrls: ['./role-form-updater.component.css']
})
export class RoleFormUpdaterComponent implements OnInit, OnChanges {

  @Input() role!: RoleDTO;
  @Input() allDisabled = false;

  updateFailed = false;

  roleUpdateForm!: RoleFormGroup;

  constructor(private privilegeManagementService: PrivilegeManagementService, private roleManagementService: RoleManagementService, private privilegeService: PrivilegeService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    this.initRoleUpdateForm();
  }

  private initRoleUpdateForm() {
    this.privilegeManagementService.companyManagementScope().then((companyManagementScope) => {
      this.roleUpdateForm = new RoleFormGroup(companyManagementScope, false, this.role);
    });
  }

  onRoleUpdate() {
    const form = this.roleUpdateForm.value;
    let companyManagementPrivilegeIdentifiers: string[] = this.roleUpdateForm.companyManagementScopeFormGroup.getSelectedPrivileges();
    let roleUpdates: RoleUpdatedInformation = {
      name: form.name,
      description: form.description,
      companyPrivilegeIdentifiers: companyManagementPrivilegeIdentifiers
    }

    console.log(roleUpdates);

    this.roleManagementService.updateRole(this.role.id, roleUpdates).then((roleUpdated) => {
      this.role = roleUpdated;
      this.updateFailed = false;
    }).catch(() => {
      this.updateFailed = true;
    })
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
