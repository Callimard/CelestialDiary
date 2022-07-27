import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {BundleManagementService} from "../../../../../service/company-management/saleable/bundle-management.service";
import {BundleCreationInformation} from "../../../../../data/company-management/saleable/bundle/bundle-creation-information";

@Component({
  selector: '[app-bundle-form-creator]',
  templateUrl: './bundle-form-creator.component.html',
  styleUrls: ['./bundle-form-creator.component.css']
})
export class BundleFormCreatorComponent implements OnInit {

  creationFailed = false;

  @Output() bundleCreated = new EventEmitter<boolean>();

  bundleCreatorForm = new FormGroup({
    name: new FormControl(null, [Validators.required]),
    description: new FormControl(null),
    suggestedPrice: new FormControl(null, [Validators.required, Validators.min(0.01)]),
    products: new FormGroup({}),
    prestations: new FormGroup({})
  })

  constructor(private bundleManagementService: BundleManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  onBundleCreation() {
    const form = this.bundleCreatorForm.value;
    const bundleCreationInformation: BundleCreationInformation = {
      name: form.name,
      description: form.description,
      suggestedPrice: form.suggestedPrice,
      products: form.products,
      prestations: form.prestations
    }

    console.log(bundleCreationInformation);

    this.bundleManagementService.createBundle(bundleCreationInformation).then(() => {
      this.bundleCreated.emit(true);
      this.creationFailed = false;
    }).catch(() => {
      this.creationFailed = true;
    })
  }
}
