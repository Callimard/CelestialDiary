import {Component, OnInit} from '@angular/core';
import {BundleDTO} from "../../../../data/company-management/saleable/bundle/bundle-dto";
import {Location} from "@angular/common";
import {ActivatedRoute} from "@angular/router";
import {BundleManagementService} from "../../../../service/company-management/saleable/bundle-management.service";

@Component({
  selector: 'app-bundle-information',
  templateUrl: './bundle-information.component.html',
  styleUrls: ['./bundle-information.component.css']
})
export class BundleInformationComponent implements OnInit {

  bundle?: BundleDTO;

  constructor(private bundleManagementService: BundleManagementService, private location: Location, private activatedRoute: ActivatedRoute) {
    this.activatedRoute.params.subscribe({
      next: (param) => {
        const bundleId: string | undefined = param["bundleId"];
        if (bundleId != null) {
          this.chargeBundle(bundleId);
        }
      }
    })
  }

  ngOnInit(): void {
    // Nothing
  }

  private chargeBundle(bundleId: string) {
    this.bundleManagementService.getSpecificBundle(bundleId).then((bundle) => {
      this.bundle = bundle;
    })
  }

  goBack() {
    this.location.back()
  }
}
