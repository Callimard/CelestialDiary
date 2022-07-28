import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {WrappedPrestationDTO} from "../../../../data/company-management/saleable/prestation/wrapped-prestation-dto";
import {PrestationManagementService} from "../../../../service/company-management/saleable/prestation-management.service";
import {WrappedProductDTO} from "../../../../data/company-management/saleable/product/wrapped-product-dto";
import {PrivilegeService} from "../../../../service/authentication/privilege.service";

@Component({
  selector: 'app-prestation-selection',
  templateUrl: './prestation-selection.component.html',
  styleUrls: ['./prestation-selection.component.css']
})
export class PrestationSelectionComponent implements OnInit {

  allPrestations: WrappedPrestationDTO[] = []

  constructor(private prestationManagementService: PrestationManagementService, private router: Router, private activatedRoute: ActivatedRoute,
              private privilegeService: PrivilegeService) {
    // Nothing
  }

  ngOnInit(): void {
    this.chargePrestations();
  }

  private chargePrestations() {
    this.prestationManagementService.allPrestations().then((allPrestations) => {
      this.allPrestations = allPrestations;
    })
  }

  filterPrestation(filter: string) {
    this.prestationManagementService.searchPrestation(filter).then((allPrestations) => {
      this.allPrestations = allPrestations;
    })
  }

  navigateToPrestationInformation(prestation: any) {
    const selectedPrestation: WrappedProductDTO = prestation as WrappedPrestationDTO;
    this.router.navigate([{outlets: {right: ['information', selectedPrestation.id]}}], {
      relativeTo: this.activatedRoute
    });
  }

  navigateToCreatePrestation() {
    this.router.navigate([{outlets: {right: ['create']}}], {
      relativeTo: this.activatedRoute
    });
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
