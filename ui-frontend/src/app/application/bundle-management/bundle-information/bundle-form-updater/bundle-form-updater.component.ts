import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {BundleDTO} from "../../../../../data/company-management/saleable/bundle/bundle-dto";
import {FormControl, FormGroup} from "@angular/forms";
import {BundleManagementService} from "../../../../../service/company-management/saleable/bundle-management.service";
import {BundleUpdatedInformation} from "../../../../../data/company-management/saleable/bundle/bundle-updated-information";

@Component({
  selector: '[app-bundle-form-updater]',
  templateUrl: './bundle-form-updater.component.html',
  styleUrls: ['./bundle-form-updater.component.css']
})
export class BundleFormUpdaterComponent implements OnInit, OnChanges {

  updateFailed = false;

  @Input() bundle!: BundleDTO;

  bundleUpdaterForm!: FormGroup

  constructor(private bundleManagementService: BundleManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    this.initUpdaterForm();
  }

  private initUpdaterForm() {
    this.bundleUpdaterForm = new FormGroup({
      name: new FormControl(this.bundle.name),
      description: new FormControl(this.bundle.description),
      suggestedPrice: new FormControl(this.bundle.suggestedPrice),
      products: new FormGroup({}),
      prestations: new FormGroup({})
    });

    if (this.bundle.products != null) {
      for (let bundleProduct of this.bundle.products) {
        this.productsFormGroup.addControl(bundleProduct.product.id, new FormControl(bundleProduct.quantity));
      }
    }

    if (this.bundle.prestations != null) {
      for (let bundlePrestation of this.bundle.prestations) {
        this.prestationsFormGroup.addControl(bundlePrestation.prestation.id, new FormControl(bundlePrestation.quantity));
      }
    }
  }

  onBundleUpdate() {
    const form = this.bundleUpdaterForm.value;
    const bundleUpdates: BundleUpdatedInformation = {
      name: form.name,
      description: form.description,
      suggestedPrice: form.suggestedPrice,
      products: form.products,
      prestations: form.prestations
    }

    this.bundleManagementService.updateBundle(this.bundle.id, bundleUpdates).then((bundle) => {
      this.bundle = bundle;
      this.updateFailed = false;
      this.initUpdaterForm();
    }).catch(() => {
      this.updateFailed = true;
    })
  }

  get productsFormGroup(): FormGroup {
    return this.bundleUpdaterForm.get('products') as FormGroup;
  }

  get prestationsFormGroup(): FormGroup {
    return this.bundleUpdaterForm.get('prestations') as FormGroup;
  }

}
