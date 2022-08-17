import {Component, OnInit} from '@angular/core';
import {PrivilegeService} from "../../../../service/authentication/privilege.service";
import {NavigationZoneComponent} from "../navigation-zone/navigation-zone.component";

@Component({
  selector: '[app-company-management-navigation]',
  templateUrl: './company-management-navigation.component.html',
  styleUrls: ['./company-management-navigation.component.css', '../navigation-zone/navigation-zone.component.css']
})
export class CompanyManagementNavigationComponent extends NavigationZoneComponent implements OnInit {

  constructor(private privilegeService: PrivilegeService) {
    super();
  }

  override ngOnInit(): void {
    super.ngOnInit();
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }

}
