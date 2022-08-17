import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {RoleDTO} from "../../../../../data/model/person/employee/role/role-dto";
import {RoleManagementService} from "../../../../../service/company-management/employee/role/role-management.service";
import {PrivilegeService} from "../../../../../service/authentication/privilege.service";

@Component({
  selector: '[app-role-information]',
  templateUrl: './role-information.component.html',
  styleUrls: ['./role-information.component.css']
})
export class RoleInformationComponent implements OnInit, OnChanges {

  @Input() roleId?: string;

  @Output() wantGoBack = new EventEmitter<boolean>();
  @Output() roleHasBeenUpdated = new EventEmitter<boolean>();

  role!: RoleDTO;

  constructor(private roleManagementService: RoleManagementService,
              private privilegeService: PrivilegeService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    if (this.roleId != null) {
      this.chargeRole(this.roleId);
    }
  }

  private chargeRole(roleId: string) {
    this.roleManagementService.getSpecificRole(roleId).then((role) => {
      this.role = role;
    })
  }

  goBack() {
    this.wantGoBack.emit(true);
  }

  deleteRole() {
    this.roleManagementService.deleteRole(this.role?.id).then(() => {
      this.roleHasBeenUpdated.emit(true);
      this.goBack();
    });
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
