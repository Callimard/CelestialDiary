import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {BundleManagementService} from "../../../../service/company-management/saleable/bundle-management.service";
import {WrappedBundleDTO} from "../../../../data/company-management/saleable/bundle/wrapped-bundle-dto";
import {WrappedProductDTO} from "../../../../data/company-management/saleable/product/wrapped-product-dto";

@Component({
  selector: 'app-bundle-selection',
  templateUrl: './bundle-selection.component.html',
  styleUrls: ['./bundle-selection.component.css']
})
export class BundleSelectionComponent implements OnInit {

  allBundles: WrappedBundleDTO[] = []

  constructor(private bundleManagementService: BundleManagementService, private router: Router, private activatedRoute: ActivatedRoute) {
    // Nothing
  }

  ngOnInit(): void {
    this.chargeBundles();
  }

  private chargeBundles() {
    this.bundleManagementService.allBundles().then((allBundles) => {
      this.allBundles = allBundles;
    })
  }

  filterBundle(filter: string) {
    this.bundleManagementService.searchBundle(filter).then((allBundles) => {
      this.allBundles = allBundles;
    })
  }

  navigateToBundleInformation(bundle: any) {
    const selectedBundle: WrappedProductDTO = bundle as WrappedBundleDTO;
    this.router.navigate([{outlets: {right: ['information', selectedBundle.id]}}], {
      relativeTo: this.activatedRoute
    });
  }

  navigateToCreateBundle() {
    this.router.navigate([{outlets: {right: ['create']}}], {
      relativeTo: this.activatedRoute
    });
  }
}
