import {Component, OnInit} from '@angular/core';
import {EstablishmentDTO} from "../../../../data/company-management/establishment/establishment-dto";
import {EstablishmentManagementService} from "../../../../service/company-management/establishment/establishment-management.service";
import {Location} from "@angular/common";
import {ActivatedRoute} from "@angular/router";
import {PrivilegeService} from "../../../../service/authentication/privilege.service";

@Component({
  selector: 'app-establishment-information',
  templateUrl: './establishment-information.component.html',
  styleUrls: ['./establishment-information.component.css']
})
export class EstablishmentInformationComponent implements OnInit {

  establishment?: EstablishmentDTO;

  constructor(private establishmentManagementService: EstablishmentManagementService, private location: Location, private activatedRoute: ActivatedRoute,
              private privilegeService: PrivilegeService) {
    this.activatedRoute.params.subscribe({
      next: (param) => {
        const establishmentId: string | undefined = param["establishmentId"];
        if (establishmentId != null) {
          this.chargeEstablishment(establishmentId);
        }
      }
    })
  }

  ngOnInit(): void {
    // Nothing
  }

  private chargeEstablishment(establishmentId: string) {
    this.establishmentManagementService.getSpecificEstablishment(establishmentId).then((establishment) => {
      this.establishment = establishment;
    })
  }

  goBack() {
    this.location.back()
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
