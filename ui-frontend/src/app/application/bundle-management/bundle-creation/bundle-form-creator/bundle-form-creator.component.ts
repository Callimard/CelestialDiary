import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {BundleManagementService} from "../../../../../service/company-management/saleable/bundle-management.service";
import {ProductManagementService} from "../../../../../service/company-management/saleable/product-management.service";
import {PrestationManagementService} from "../../../../../service/company-management/saleable/prestation-management.service";
import {WrappedPrestationDTO} from "../../../../../data/company-management/saleable/prestation/wrapped-prestation-dto";
import {WrappedProductDTO} from "../../../../../data/company-management/saleable/product/wrapped-product-dto";
import {BundleCreationInformation} from "../../../../../data/company-management/saleable/bundle/bundle-creation-information";

@Component({
  selector: '[app-bundle-form-creator]',
  templateUrl: './bundle-form-creator.component.html',
  styleUrls: ['./bundle-form-creator.component.css']
})
export class BundleFormCreatorComponent implements OnInit {

  creationFailed = false;

  @Output() bundleCreated = new EventEmitter<boolean>();

  allProducts: WrappedProductDTO[] = [];
  availableProducts: WrappedProductDTO[] = [];
  chosenProducts: WrappedProductDTO[] = [];
  productTotalPrice: number = 0;

  allPrestations: WrappedPrestationDTO[] = [];
  availablePrestations: WrappedPrestationDTO[] = [];
  chosenPrestations: WrappedPrestationDTO[] = [];
  prestationTotalPrice: number = 0;

  bundleCreatorForm = new FormGroup({
    name: new FormControl(null, [Validators.required]),
    description: new FormControl(null),
    suggestedPrice: new FormControl(null, [Validators.required, Validators.min(0.01)]),
  })

  constructor(private bundleManagementService: BundleManagementService,
              private productManagementService: ProductManagementService,
              private prestationManagementService: PrestationManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    this.chargeAllProducts();
    this.chargeAllPrestations();
  }

  private chargeAllProducts() {
    this.productManagementService.allProducts().then((allProducts) => {
      this.allProducts = allProducts;
      for (let product of this.allProducts) {
        this.availableProducts.push(product);
      }
    });
  }

  private chargeAllPrestations() {
    this.prestationManagementService.allPrestations().then((allPrestations) => {
      this.allPrestations = allPrestations;
      for (let prestation of this.allPrestations) {
        this.availablePrestations.push(prestation);
      }
    })
  }

  onBundleCreation() {
    const form = this.bundleCreatorForm.value;
    const bundleCreationInformation: BundleCreationInformation = {
      name: form.name,
      description: form.description,
      suggestedPrice: form.suggestedPrice,
      products: this.chosenProducts.map(p => p.id),
      prestations: this.chosenPrestations.map(p => p.id)
    }

    this.bundleManagementService.createBundle(bundleCreationInformation).then(() => {
      this.bundleCreated.emit(true);
      this.creationFailed = false;
    }).catch(() => {
      this.creationFailed = true;
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
