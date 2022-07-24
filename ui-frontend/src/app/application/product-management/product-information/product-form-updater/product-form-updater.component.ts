import {Component, Input, OnInit} from '@angular/core';
import {ProductDTO} from "../../../../../data/company-management/saleable/product/product-dto";
import {FormControl, FormGroup} from "@angular/forms";
import {ProductManagementService} from "../../../../../service/company-management/product-management.service";
import {ProductUpdatedInformation} from "../../../../../data/company-management/saleable/product/product-updated-information";

@Component({
  selector: '[app-product-form-updater]',
  templateUrl: './product-form-updater.component.html',
  styleUrls: ['./product-form-updater.component.css']
})
export class ProductFormUpdaterComponent implements OnInit {

  @Input() product!: ProductDTO

  updateFailed = false;

  productUpdateForm!: FormGroup;

  constructor(private productManagementService: ProductManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    this.productUpdateForm = new FormGroup(
      {
        name: new FormControl(this.product.name),
        description: new FormControl(this.product.description),
        suggestedPrice: new FormControl(this.product.suggestedPrice),
      }
    );
  }

  onProductUpdate() {
    const form = this.productUpdateForm.value;
    const productUpdates: ProductUpdatedInformation = {
      name: form.name,
      description: form.description,
      suggestedPrice: form.suggestedPrice
    }
    this.productManagementService.updateProduct(this.product.id, productUpdates).then((wrappedProduct) => {
      this.updateFailed = false;
      this.product.name = wrappedProduct.name;
      this.product.description = wrappedProduct.description;
      this.product.suggestedPrice = wrappedProduct.suggestedPrice;
      this.product.activated = wrappedProduct.activated;
    }).catch(() => {
      this.updateFailed = true
    })
  }

}
