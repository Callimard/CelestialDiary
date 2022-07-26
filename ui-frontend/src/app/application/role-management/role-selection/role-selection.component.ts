import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {RoleManagementService} from "../../../../service/company-management/role-management.service";
import {RoleDTO} from "../../../../data/company-management/employee/role-dto";

@Component({
  selector: 'app-role-selection',
  templateUrl: './role-selection.component.html',
  styleUrls: ['./role-selection.component.css']
})
export class RoleSelectionComponent implements OnInit {

  allRoles: RoleDTO[] = [];

  constructor(private roleManagementService: RoleManagementService, private router: Router, private activatedRoute: ActivatedRoute) {
    // Nothing
  }

  ngOnInit(): void {
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

  navigateToRoleInformation(role: any) {
    const selectedRole: RoleDTO = role as RoleDTO;
    this.router.navigate([{outlets: {right: ['information', selectedRole.id]}}], {
      relativeTo: this.activatedRoute
    });
  }

  navigateToCreateRole() {
    this.router.navigate([{outlets: {right: ['create']}}], {
      relativeTo: this.activatedRoute
    });
  }

}
