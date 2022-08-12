import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {RoleDTO} from "../../../../../../data/model/person/employee/role/role-dto";
import {RoleUpdatedInformation} from "../../../../../../data/model/person/employee/role/role-updated-information";
import {RoleManagementService} from "../../../../../../service/company-management/employee/role/role-management.service";
import {PrivilegeService} from "../../../../../../service/authentication/privilege.service";
import {RoleFormGroup} from "../../utils/role-form-group";
import {PrivilegeManagementService} from "../../../../../../service/security/privilege/privilege-management.service";
import {EstablishmentManagementService} from "../../../../../../service/company-management/establishment/establishment-management.service";

@Component({
  selector: '[app-role-form-updater]',
  templateUrl: './role-form-updater.component.html',
  styleUrls: ['./role-form-updater.component.css']
})
export class RoleFormUpdaterComponent implements OnInit, OnChanges {

  @Input() role!: RoleDTO;
  @Input() allDisabled = false;

  @Output() hasBeenUpdated = new EventEmitter<boolean>();

  updateFailed = false;

  roleUpdateForm!: RoleFormGroup;

  constructor(private privilegeManagementService: PrivilegeManagementService,
              private establishmentManagementService: EstablishmentManagementService,
              private roleManagementService: RoleManagementService, private privilegeService: PrivilegeService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    this.initRoleUpdateForm();
  }

  private initRoleUpdateForm() {
    this.roleUpdateForm = new RoleFormGroup(this.privilegeManagementService, this.establishmentManagementService, false, this.role);
  }

  onRoleUpdate() {
    let roleUpdates: RoleUpdatedInformation = this.roleUpdateForm.extractRoleInformation() as RoleUpdatedInformation

    this.roleManagementService.updateRole(this.role.id, roleUpdates).then((roleUpdated) => {
      this.role = roleUpdated;
      this.updateFailed = false;
      this.hasBeenUpdated.emit(true);
    }).catch(() => {
      this.updateFailed = true;
      this.hasBeenUpdated.emit(false);
    })
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
