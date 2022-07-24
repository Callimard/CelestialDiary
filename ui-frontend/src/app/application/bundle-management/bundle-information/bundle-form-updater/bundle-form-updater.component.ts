import {Component, Input, OnInit} from '@angular/core';
import {BundleDTO} from "../../../../../data/company-management/saleable/bundle/bundle-dto";
import {FormControl, FormGroup} from "@angular/forms";
import {BundleManagementService} from "../../../../../service/company-management/bundle-management.service";
import {ProductManagementService} from "../../../../../service/company-management/product-management.service";
import {PrestationManagementService} from "../../../../../service/company-management/prestation-management.service";
import {WrappedProductDTO} from "../../../../../data/company-management/saleable/product/wrapped-product-dto";
import {WrappedPrestationDTO} from "../../../../../data/company-management/saleable/prestation/wrapped-prestation-dto";
import {BundleUpdatedInformation} from "../../../../../data/company-management/saleable/bundle/bundle-updated-information";

@Component({
  selector: '[app-bundle-form-updater]',
  templateUrl: './bundle-form-updater.component.html',
  styleUrls: ['./bundle-form-updater.component.css']
})
export class BundleFormUpdaterComponent implements OnInit {

  updateFailed = false;

  @Input() bundle!: BundleDTO;

  allProducts: WrappedProductDTO[] = [];
  availableProducts: WrappedProductDTO[] = [];
  chosenProducts: WrappedProductDTO[] = [];
  productTotalPrice: number = 0;

  allPrestations: WrappedPrestationDTO[] = [];
  availablePrestations: WrappedPrestationDTO[] = [];
  chosenPrestations: WrappedPrestationDTO[] = [];
  prestationTotalPrice: number = 0;

  bundleUpdaterForm!: FormGroup

  constructor(private bundleManagementService: BundleManagementService,
              private productManagementService: ProductManagementService,
              private prestationManagementService: PrestationManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    this.bundleUpdaterForm = new FormGroup({
      name: new FormControl(this.bundle.name),
      description: new FormControl(this.bundle.description),
      suggestedPrice: new FormControl(this.bundle.suggestedPrice),
    });

    console.log(this.bundle);

    this.chargeAllProducts();
    this.chargeAllPrestations();
  }

  private chargeAllProducts() {
    this.productManagementService.allProducts().then((allProducts) => {
      this.allProducts = allProducts;
      for (let product of this.allProducts) {
        this.availableProducts.push(product);
      }
      this.mergeProductInformation();
    });
  }

  private chargeAllPrestations() {
    this.prestationManagementService.allPrestations().then((allPrestations) => {
      this.allPrestations = allPrestations;
      for (let prestation of this.allPrestations) {
        this.availablePrestations.push(prestation);
      }
      this.mergePrestationInformation();
    })
  }

  private mergeProductInformation() {
    if (this.bundle.products != null) {
      let chosen: WrappedProductDTO[] = [];
      for (let product of this.availableProducts) {
        for (let bundleProduct of this.bundle.products) {
          if (bundleProduct.id === product.id) {
            chosen.push(product);
          }
        }
      }

      for (let product of chosen) {
        this.choseProduct(product);
      }
    }
  }

  private mergePrestationInformation() {
    if (this.bundle.prestations != null) {
      let chosen: WrappedPrestationDTO[] = [];
      for (let prestation of this.availablePrestations) {
        for (let bundlePrestation of this.bundle.prestations) {
          if (bundlePrestation.id === prestation.id) {
            chosen.push(prestation);
          }
        }
      }

      for (let prestation of chosen) {
        this.chosePrestation(prestation);
      }
    }
  }

  onBundleUpdate() {
    const form = this.bundleUpdaterForm.value;
    const bundleUpdates: BundleUpdatedInformation = {
      name: form.name,
      description: form.description,
      suggestedPrice: form.suggestedPrice,
      products: this.chosenProducts.map(p => p.id),
      prestations: this.chosenPrestations.map(p => p.id)
    }

    this.bundleManagementService.updateBundle(this.bundle.id, bundleUpdates).then((wrappedBundle) => {
      this.bundle.name = wrappedBundle.name;
      this.bundle.description = wrappedBundle.description;
      this.bundle.suggestedPrice = wrappedBundle.suggestedPrice;
      this.bundle.activated = wrappedBundle.activated;

      this.updateFailed = false;
    }).catch(() => {
      this.updateFailed = true;
    })
  }

  choseProduct(product: WrappedProductDTO) {
    this.availableProducts.splice(this.availableProducts.indexOf(product), 1);
    this.chosenProducts.push(product);
    this.productTotalPrice += product.suggestedPrice;
  }

  removeProduct(product: WrappedProductDTO) {
    this.chosenProducts.splice(this.chosenProducts.indexOf(product), 1);
    this.availableProducts.push(product);
    this.productTotalPrice -= product.suggestedPrice;
  }

  chosePrestation(prestation: WrappedPrestationDTO) {
    this.availablePrestations.splice(this.availablePrestations.indexOf(prestation), 1);
    this.chosenPrestations.push(prestation);
    this.prestationTotalPrice += prestation.suggestedPrice;
  }

  removePrestation(prestation: WrappedPrestationDTO) {
    this.chosenPrestations.splice(this.chosenPrestations.indexOf(prestation), 1);
    this.availablePrestations.push(prestation);
    this.prestationTotalPrice -= prestation.suggestedPrice;
  }
}
