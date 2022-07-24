import {Component, OnInit} from '@angular/core';
import {WrappedEstablishmentDTO} from "../../../../data/company-management/establishment/wrapped-establishment-dto";
import {ActivatedRoute, Router} from "@angular/router";
import {EstablishmentManagementService} from "../../../../service/company-management/establishment-management.service";

@Component({
  selector: 'app-establishment-selection',
  templateUrl: './establishment-selection.component.html',
  styleUrls: ['./establishment-selection.component.css']
})
export class EstablishmentSelectionComponent implements OnInit {

  allEstablishments: WrappedEstablishmentDTO[] = [];

  constructor(private establishmentManagementService: EstablishmentManagementService, private router: Router, private activatedRoute: ActivatedRoute) {
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
    const selectedEstablishment: WrappedEstablishmentDTO = establishment as WrappedEstablishmentDTO;
    this.router.navigate([{outlets: {right: ['information', selectedEstablishment.id]}}], {
      relativeTo: this.activatedRoute
    });
  }

  navigateToCreateEstablishment() {
    this.router.navigate([{outlets: {right: ['create']}}], {
      relativeTo: this.activatedRoute
    });
  }

}
