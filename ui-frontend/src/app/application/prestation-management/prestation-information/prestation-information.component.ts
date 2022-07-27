import {Component, OnInit} from '@angular/core';
import {PrestationDTO} from "../../../../data/company-management/saleable/prestation/prestation-dto";
import {Location} from "@angular/common";
import {ActivatedRoute} from "@angular/router";
import {PrestationManagementService} from "../../../../service/company-management/saleable/prestation-management.service";

@Component({
  selector: 'app-prestation-information',
  templateUrl: './prestation-information.component.html',
  styleUrls: ['./prestation-information.component.css']
})
export class PrestationInformationComponent implements OnInit {

  prestation?: PrestationDTO;

  constructor(private prestationManagementService: PrestationManagementService, private location: Location, private activatedRoute: ActivatedRoute) {
    this.activatedRoute.params.subscribe({
      next: (param) => {
        const prestationId: string | undefined = param["prestationId"];
        if (prestationId != null) {
          this.chargePrestation(prestationId);
        }
      }
    })
  }

  ngOnInit(): void {
    // Nothing
  }

  private chargePrestation(prestationId: string) {
    this.prestationManagementService.getSpecificPrestation(prestationId).then((prestation) => {
      this.prestation = prestation;
    })
  }

  goBack() {
    this.location.back()
  }
}
