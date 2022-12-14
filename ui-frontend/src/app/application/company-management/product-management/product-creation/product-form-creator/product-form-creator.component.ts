import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ProductManagementService} from "../../../../../../service/company-management/saleable/product-management.service";
import {ProductCreationInformation} from "../../../../../../data/model/saleable/product/product-creation-information";

@Component({
  selector: '[app-product-form-creator]',
  templateUrl: './product-form-creator.component.html',
  styleUrls: ['./product-form-creator.component.css']
})
export class ProductFormCreatorComponent implements OnInit {

  creationFailed = false;

  @Output() productCreated = new EventEmitter<boolean>();

  productCreatorForm = new FormGroup({
    name: new FormControl(null, [Validators.required]),
    description: new FormControl(null),
    suggestedPrice: new FormControl(null, [Validators.required, Validators.min(0.01)]),
  });

  constructor(private productManagementService: ProductManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  onProductCreation() {
    const form = this.productCreatorForm.value;
    const productInformation: ProductCreationInformation = {
      name: form.name,
      description: form.description,
      suggestedPrice: form.suggestedPrice
    }
    this.productManagementService.createProduct(productInformation).then(() => {
      this.productCreated.emit(true);
      this.creationFailed = false;
    }).catch(() => {
      this.creationFailed = true;
    })
  }
}
