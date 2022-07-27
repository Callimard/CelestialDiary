import {Component, OnInit} from '@angular/core';
import {RoleDTO} from "../../../../data/company-management/employee/role-dto";
import {ActivatedRoute} from "@angular/router";
import {RoleManagementService} from "../../../../service/company-management/employee/role/role-management.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-role-information',
  templateUrl: './role-information.component.html',
  styleUrls: ['./role-information.component.css']
})
export class RoleInformationComponent implements OnInit {

  role!: RoleDTO;

  constructor(private roleManagementService: RoleManagementService, private location: Location, private activatedRoute: ActivatedRoute) {
    this.activatedRoute.params.subscribe({
      next: (param) => {
        const roleId: string | undefined = param["roleId"];
        if (roleId != null) {
          this.chargeRole(roleId);
        }
      }
    })
  }

  ngOnInit(): void {
    // Nothing
  }

  private chargeRole(roleId: string) {
    this.roleManagementService.getSpecificRole(roleId).then((role) => {
      this.role = role;
    })
  }

  goBack() {
    this.location.back();
  }

  deleteRole() {
    this.roleManagementService.deleteRole(this.role?.id).then(() => {
      this.goBack();
    });
  }

}
