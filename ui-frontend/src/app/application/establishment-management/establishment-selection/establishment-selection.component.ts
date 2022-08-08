import {Component, OnInit} from '@angular/core';
import {WrappedEstablishmentDTO} from "../../../../data/company-management/establishment/wrapped-establishment-dto";
import {ActivatedRoute, Router} from "@angular/router";
import {EstablishmentManagementService} from "../../../../service/company-management/establishment/establishment-management.service";
import {PrivilegeService} from "../../../../service/authentication/privilege.service";
import {PaneInfoTransformer, PaneInfoWithId} from "../../../libairy/informative/info-pane/info-pane.component";

export class EstablishmentPaneInfoTransformer implements PaneInfoTransformer<WrappedEstablishmentDTO> {
  transform(establishment: WrappedEstablishmentDTO): PaneInfoWithId {
    return {
      id: establishment.id,
      title: establishment.name,
      subTitle: establishment.description,
      img: establishment.photo
    };
  }

}

@Component({
  selector: 'app-establishment-selection',
  templateUrl: './establishment-selection.component.html',
  styleUrls: ['./establishment-selection.component.css']
})
export class EstablishmentSelectionComponent implements OnInit {

  allEstablishments: WrappedEstablishmentDTO[] = [];

  establishmentPaneInfoTransformer: PaneInfoTransformer<WrappedEstablishmentDTO> = new EstablishmentPaneInfoTransformer();

  constructor(private establishmentManagementService: EstablishmentManagementService, private router: Router, private activatedRoute: ActivatedRoute,
              private privilegeService: PrivilegeService) {
    // Nothing
  }

  ngOnInit(): void {
    this.chargeEstablishments();
  }

  private chargeEstablishments() {
    this.establishmentManagementService.allEstablishments().then((allEstablishments) => {
      this.allEstablishments = allEstablishments;
    });
  }

  filterEstablishment(filter: string) {
    this.establishmentManagementService.searchEstablishment(filter).then((allEstablishments) => {
      this.allEstablishments = allEstablishments;
    });
  }

  navigateToEstablishmentInformation(establishment: any) {
    const selectedEstablishment: PaneInfoWithId = establishment as PaneInfoWithId;
    this.router.navigate([{outlets: {right: ['information', selectedEstablishment.id]}}], {
      relativeTo: this.activatedRoute
    });
  }

  navigateToCreateEstablishment() {
    this.router.navigate([{outlets: {right: ['create']}}], {
      relativeTo: this.activatedRoute
    });
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
