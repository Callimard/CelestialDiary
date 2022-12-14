import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {ProductDTO} from "../../../../../../data/model/saleable/product/product-dto";
import {FormControl, FormGroup} from "@angular/forms";
import {ProductManagementService} from "../../../../../../service/company-management/saleable/product-management.service";
import {ProductUpdatedInformation} from "../../../../../../data/model/saleable/product/product-updated-information";

@Component({
  selector: '[app-product-form-updater]',
  templateUrl: './product-form-updater.component.html',
  styleUrls: ['./product-form-updater.component.css']
})
export class ProductFormUpdaterComponent implements OnInit, OnChanges {

  @Input() product!: ProductDTO
  @Input() allDisabled = false;

  @Output() hasBeenUpdated = new EventEmitter<boolean>();

  updateFailed = false;

  productUpdateForm!: FormGroup;

  constructor(private productManagementService: ProductManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    this.initUpdaterForm();
  }

  private initUpdaterForm() {
    this.productUpdateForm = new FormGroup(
      {
        name: new FormControl({value: this.product.name, disabled: this.allDisabled}),
        description: new FormControl({value: this.product.description, disabled: this.allDisabled}),
        suggestedPrice: new FormControl({value: this.product.suggestedPrice, disabled: this.allDisabled}),
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
      this.hasBeenUpdated.emit(true);
      this.updateFailed = false;

      this.product.name = wrappedProduct.name;
      this.product.description = wrappedProduct.description;
      this.product.suggestedPrice = wrappedProduct.suggestedPrice;
      this.product.activated = wrappedProduct.activated;
    }).catch(() => {
      this.updateFailed = true
      this.hasBeenUpdated.emit(false);
    })
  }

}
