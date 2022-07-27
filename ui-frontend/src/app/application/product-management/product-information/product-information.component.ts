import {Component, OnInit} from '@angular/core';
import {Location} from "@angular/common";
import {ActivatedRoute} from "@angular/router";
import {ProductDTO} from "../../../../data/company-management/saleable/product/product-dto";
import {ProductManagementService} from "../../../../service/company-management/saleable/product-management.service";

@Component({
  selector: 'app-product-information',
  templateUrl: './product-information.component.html',
  styleUrls: ['./product-information.component.css']
})
export class ProductInformationComponent implements OnInit {

  product?: ProductDTO;

  constructor(private productManagementService: ProductManagementService, private location: Location, private activatedRoute: ActivatedRoute) {
    this.activatedRoute.params.subscribe({
      next: (param) => {
        const productId: string | undefined = param["productId"];
        if (productId != null) {
          this.chargeProduct(productId);
        }
      }
    })
  }

  ngOnInit(): void {
    // nothing
  }

  private chargeProduct(productId: string) {
    this.productManagementService.getSpecificProduct(productId).then((product) => {
      this.product = product;
    })
  }

  goBack() {
    this.location.back()
  }
}
