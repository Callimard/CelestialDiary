import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {RoleManagementService} from "../../../../service/company-management/employee/role/role-management.service";
import {RoleDTO} from "../../../../data/company-management/person/employee/role/role-dto";
import {PrivilegeService} from "../../../../service/authentication/privilege.service";
import {PaneInfoTransformer, PaneInfoWithId} from "../../../library/informative/info-pane/info-pane.component";

export class RolePaneInfoTransformer implements PaneInfoTransformer<RoleDTO> {
  transform(role: RoleDTO): PaneInfoWithId {
    return {
      id: role.id,
      title: role.name,
      subTitle: role.description,
    };
  }
}

@Component({
  selector: '[app-role-selection]',
  templateUrl: './role-selection.component.html',
  styleUrls: ['./role-selection.component.css']
})
export class RoleSelectionComponent implements OnInit {

  @Output() roleSelected = new EventEmitter<string>();
  @Output() wantCreateRole = new EventEmitter<boolean>();

  allRoles: RoleDTO[] = [];

  rolePaneInfoTransformer: PaneInfoTransformer<RoleDTO> = new RolePaneInfoTransformer();

  constructor(private roleManagementService: RoleManagementService,
              private privilegeService: PrivilegeService) {
    // Nothing
  }

  ngOnInit(): void {
    this.chargeRoles();
  }

  public reload() {
    this.chargeRoles();
  }

  private chargeRoles() {
    this.roleManagementService.allRoles().then((allRoles) => {
      this.allRoles = allRoles;
    })
  }

  filterRole(filter: string) {
    this.roleManagementService.searchRole(filter).then((allRoles) => {
      this.allRoles = allRoles;
    })
  }

  selectRole(role: any) {
    const selectedRole: PaneInfoWithId = role as PaneInfoWithId;
    this.roleSelected.emit(selectedRole.id);
  }

  createRole() {
    this.wantCreateRole.emit(true);
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
