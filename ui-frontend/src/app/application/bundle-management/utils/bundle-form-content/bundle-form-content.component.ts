import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {WrappedProductDTO} from "../../../../../data/company-management/saleable/product/wrapped-product-dto";
import {WrappedPrestationDTO} from "../../../../../data/company-management/saleable/prestation/wrapped-prestation-dto";
import {ProductManagementService} from "../../../../../service/company-management/saleable/product-management.service";
import {PrestationManagementService} from "../../../../../service/company-management/saleable/prestation-management.service";

@Component({
  selector: '[app-bundle-form-content]',
  templateUrl: './bundle-form-content.component.html',
  styleUrls: ['./bundle-form-content.component.css']
})
export class BundleFormContentComponent implements OnInit {

  @Input() bundleFormGroup!: FormGroup

  allProducts: WrappedProductDTO[] = [];
  availableProducts: WrappedProductDTO[] = [];
  chosenProducts: WrappedProductDTO[] = [];
  productTotalPrice: number = 0;

  allPrestations: WrappedPrestationDTO[] = [];
  availablePrestations: WrappedPrestationDTO[] = [];
  chosenPrestations: WrappedPrestationDTO[] = [];
  prestationTotalPrice: number = 0;

  constructor(private productManagementService: ProductManagementService,
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

  choseProduct(product: WrappedProductDTO) {
    this.availableProducts.splice(this.availableProducts.indexOf(product), 1);
    this.productsFormGroup.addControl(product.id, new FormControl(1, [Validators.min(1)]));
    this.chosenProducts.push(product);
    this.productTotalPrice += product.suggestedPrice;
  }

  removeProduct(product: WrappedProductDTO) {
    this.chosenProducts.splice(this.chosenProducts.indexOf(product), 1);
    this.productsFormGroup.removeControl(product.id);
    this.availableProducts.push(product);
    this.productTotalPrice -= product.suggestedPrice;
  }

  chosePrestation(prestation: WrappedPrestationDTO) {
    this.availablePrestations.splice(this.availablePrestations.indexOf(prestation), 1);
    this.prestationsFormGroup.addControl(prestation.id, new FormControl(1, [Validators.min(1)]));
    this.chosenPrestations.push(prestation);
    this.prestationTotalPrice += prestation.suggestedPrice;
  }

  removePrestation(prestation: WrappedPrestationDTO) {
    this.chosenPrestations.splice(this.chosenPrestations.indexOf(prestation), 1);
    this.prestationsFormGroup.removeControl(prestation.id);
    this.availablePrestations.push(prestation);
    this.prestationTotalPrice -= prestation.suggestedPrice;
  }

  get productsFormGroup(): FormGroup {
    return this.bundleFormGroup.get('products') as FormGroup;
  }

  get prestationsFormGroup(): FormGroup {
    return this.bundleFormGroup.get('prestations') as FormGroup;
  }
}
