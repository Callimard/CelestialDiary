import {Component, OnInit} from '@angular/core';
import {NavigationZoneComponent} from "../navigation-zone/navigation-zone.component";
import {PrivilegeService} from "../../../../service/authentication/privilege.service";
import {JwtAccount} from "../../../../service/authentication/jwt-account";
import {AuthenticationService} from "../../../../service/authentication/authentication.service";
import {WrappedEstablishmentDTO} from "../../../../data/company-management/establishment/wrapped-establishment-dto";
import {EstablishmentManagementService} from "../../../../service/company-management/establishment/establishment-management.service";

@Component({
  selector: '[app-establishment-management-navigation]',
  templateUrl: './establishment-management-navigation.component.html',
  styleUrls: ['./establishment-management-navigation.component.css', '../navigation-zone/navigation-zone.component.css']
})
export class EstablishmentManagementNavigationComponent extends NavigationZoneComponent implements OnInit {

  jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();

  establishments: WrappedEstablishmentDTO[] = [];

  constructor(private establishmentManagementService: EstablishmentManagementService, private privilegeService: PrivilegeService) {
    super();
  }

  override ngOnInit(): void {
    super.ngOnInit();
    this.chargeEstablishments();
  }

  chargeEstablishments() {
    const establishmentIds: string[] = Object.keys(this.jwtAccount.establishmentPrivilegeIdentifiers);
    if (establishmentIds.length > 0) {
      this.establishmentManagementService.searchEstablishmentWithId(establishmentIds).then((allEstablishments) => {
        this.establishments = allEstablishments;
      });
    }
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
