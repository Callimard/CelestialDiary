import {Component, OnInit} from '@angular/core';
import {NavigationZoneComponent} from "../navigation-zone/navigation-zone.component";
import {PrivilegeService} from "../../../../service/authentication/privilege.service";
import {JwtAccount} from "../../../../service/authentication/jwt-account";
import {AuthenticationService} from "../../../../service/authentication/authentication.service";
import {WrappedEstablishmentDTO} from "../../../../data/model/establishment/wrapped-establishment-dto";
import {EstablishmentManagementService} from "../../../../service/company-management/establishment/establishment-management.service";
import {NavItemElem} from "../nav-list-item/nav-list-item.component";

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
    if (this.privilegeService.hasCompanyAll()) {
      this.establishmentManagementService.allEstablishments().then((allEstablishments) => {
        this.establishments = allEstablishments;
      })
    } else {
      const allEstablishmentIds: string[] = Object.keys(this.jwtAccount.establishmentPrivilegeIdentifiers);
      if (allEstablishmentIds.length > 0) {
        // Jwt account also contains establishment id for which with do not have privileges however le privilege array is empty
        let neededEstablishments: string[] = [];
        for (let establishmentId of allEstablishmentIds) {
          if (this.jwtAccount.establishmentPrivilegeIdentifiers[establishmentId].length > 0) {
            neededEstablishments.push(establishmentId);
          }
        }

        this.establishmentManagementService.searchEstablishmentWithId(neededEstablishments).then((establishments) => {
          this.establishments = establishments;
        });
      }
    }
  }

  createNavItemElem(icon: string, title: string, routeLink: string[], canBeDisplay: boolean): NavItemElem {
    return {
      icon: icon,
      title: title,
      routeLink: routeLink,
      canBeDisplay: canBeDisplay
    } as NavItemElem;
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
