import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {WrappedProductDTO} from "../../../../../data/company-management/saleable/product/wrapped-product-dto";
import {WrappedPrestationDTO} from "../../../../../data/company-management/saleable/prestation/wrapped-prestation-dto";
import {ProductManagementService} from "../../../../../service/company-management/saleable/product-management.service";
import {PrestationManagementService} from "../../../../../service/company-management/saleable/prestation-management.service";
import {PaneInfoTransformer} from "../../../../library/informative/info-pane/info-pane.component";
import {WrappedSaleableDTO} from "../../../../../data/company-management/saleable/wrapped-saleable-dto";
import {SaleablePaneInfoTransformer} from "../../../utils/saleable-pane-info-transformer";

@Component({
  selector: '[app-bundle-form-content]',
  templateUrl: './bundle-form-content.component.html',
  styleUrls: ['./bundle-form-content.component.css']
})
export class BundleFormContentComponent implements OnInit, OnChanges {

  @Input() bundleFormGroup!: FormGroup
  @Input() allDisabled = false;

  allProducts = new Map<string, WrappedProductDTO>();
  availableProducts: WrappedProductDTO[] = [];
  productTotalPrice: number = 0;

  allPrestations = new Map<string, WrappedPrestationDTO>();
  availablePrestations: WrappedPrestationDTO[] = [];
  prestationTotalPrice: number = 0;

  saleablePaneInfoTransformer: PaneInfoTransformer<WrappedSaleableDTO> = new SaleablePaneInfoTransformer();

  constructor(private productManagementService: ProductManagementService,
              private prestationManagementService: PrestationManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    this.chargeAllProducts();
    this.chargeAllPrestations();
  }

  private chargeAllProducts() {
    this.productManagementService.allProducts().then((allProducts) => {
      for (let product of allProducts) {
        this.allProducts.set(product.id, product);
        this.availableProducts.push(product);
        this.mergeProductChosen();
        this.computeProductTotalPrice();
      }
    });
  }

  private mergeProductChosen() {
    let toRemove: WrappedProductDTO[] = [];
    const keys = this.productFormGroupKeys;
    for (let product of this.availableProducts) {
      for (let key of keys) {
        if (product.id === key) {
          toRemove.push(product);
        }
      }
    }

    for (let product of toRemove) {
      this.availableProducts.splice(this.availableProducts.indexOf(product), 1);
    }

    if (this.allDisabled) {
      this.bundleFormGroup.disable();
    }
  }

  private chargeAllPrestations() {
    this.prestationManagementService.allPrestations().then((allPrestations) => {
      for (let prestation of allPrestations) {
        this.allPrestations.set(prestation.id, prestation);
        this.availablePrestations.push(prestation);
        this.mergePrestationChosen();
        this.computePrestationTotalPrice();
      }
    })
  }

  private mergePrestationChosen() {
    let toRemove: WrappedPrestationDTO[] = [];
    const keys = this.prestationFormGroupKeys;
    for (let prestation of this.availablePrestations) {
      for (let key of keys) {
        if (prestation.id === key) {
          toRemove.push(prestation);
        }
      }
    }

    for (let prestation of toRemove) {
      this.availablePrestations.splice(this.availablePrestations.indexOf(prestation), 1);
    }

    if (this.allDisabled) {
      this.bundleFormGroup.disable();
    }
  }

  getProduct(productId: string): WrappedProductDTO | null {
    const product = this.allProducts.get(productId);
    return product != null ? product : null;
  }

  getProductName(productId: string): string {
    const product: WrappedProductDTO | null = this.getProduct(productId);
    if (product != null) {
      return product.name;
    } else {
      return "";
    }
  }

  choseProduct(product: WrappedProductDTO) {
    if (!this.allDisabled) {
      this.availableProducts.splice(this.availableProducts.indexOf(product), 1);
      this.productsFormGroup.addControl(product.id, new FormControl(1, [Validators.min(1)]));
      this.computeProductTotalPrice();
    }
  }

  removeProduct(productId: string) {
    const product = this.getProduct(productId);
    if (product != null) {
      this.productsFormGroup.removeControl(product.id);
      this.availableProducts.push(product);
      this.computeProductTotalPrice();
    }
  }

  computeProductTotalPrice() {
    const productIds: string[] = this.productFormGroupKeys;
    this.productTotalPrice = 0;
    for (let productId of productIds) {
      const product = this.allProducts.get(productId);
      if (product != null) {
        const quantity = this.productsFormGroup.value[productId];
        this.productTotalPrice += product.suggestedPrice * quantity;
      }
    }
  }

  getPrestation(prestationId: string): WrappedPrestationDTO | null {
    const prestation = this.allPrestations.get(prestationId);
    return prestation != null ? prestation : null;
  }

  getPrestationName(prestationId: string): string {
    const prestation: WrappedPrestationDTO | null = this.getPrestation(prestationId);
    if (prestation != null) {
      return prestation.name;
    } else {
      return "";
    }
  }

  chosePrestation(prestation: WrappedPrestationDTO) {
    if (!this.allDisabled) {
      this.availablePrestations.splice(this.availablePrestations.indexOf(prestation), 1);
      this.prestationsFormGroup.addControl(prestation.id, new FormControl(1, [Validators.min(1)]));
      this.computePrestationTotalPrice();
    }
  }

  removePrestation(prestationId: string) {
    const prestation = this.getPrestation(prestationId);

    if (prestation != null) {
      this.prestationsFormGroup.removeControl(prestation.id);
      this.availablePrestations.push(prestation);
      this.computePrestationTotalPrice();
    }
  }

  computePrestationTotalPrice() {
    const prestationIds: string[] = this.prestationFormGroupKeys;
    this.prestationTotalPrice = 0;
    for (let prestationId of prestationIds) {
      const prestation = this.allPrestations.get(prestationId);
      if (prestation != null) {
        const quantity = this.prestationsFormGroup.value[prestationId];
        this.prestationTotalPrice += prestation.suggestedPrice * quantity;
      }
    }
  }

  get productsFormGroup(): FormGroup {
    return this.bundleFormGroup.get('products') as FormGroup;
  }

  get productFormGroupKeys(): string[] {
    return Object.keys(this.productsFormGroup.value);
  }

  get prestationsFormGroup(): FormGroup {
    return this.bundleFormGroup.get('prestations') as FormGroup;
  }

  get prestationFormGroupKeys(): string[] {
    return Object.keys(this.prestationsFormGroup.value);
  }
}
