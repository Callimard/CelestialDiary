import {Component, OnInit} from '@angular/core';
import {WrappedProductDTO} from "../../../../data/company-management/saleable/product/wrapped-product-dto";
import {ActivatedRoute, Router} from "@angular/router";
import {ProductManagementService} from "../../../../service/company-management/saleable/product-management.service";
import {PrivilegeService} from "../../../../service/authentication/privilege.service";
import {PaneInfoTransformer, PaneInfoWithId} from "../../../libairy/informative/info-pane/info-pane.component";
import {WrappedSaleableDTO} from "../../../../data/company-management/saleable/wrapped-saleable-dto";
import {SaleablePaneInfoTransformer} from "../../utils/saleable-pane-info-transformer";

@Component({
  selector: '[app-product-management-left-side]',
  templateUrl: './product-selection.component.html',
  styleUrls: ['./product-selection.component.css']
})
export class ProductSelectionComponent implements OnInit {

  allProducts: WrappedProductDTO[] = [];

  saleablePaneInfoTransformer: PaneInfoTransformer<WrappedSaleableDTO> = new SaleablePaneInfoTransformer();

  constructor(private productManagementService: ProductManagementService, private router: Router, private activatedRoute: ActivatedRoute,
              private privilegeService: PrivilegeService) {
    // Nothing
  }

  ngOnInit(): void {
    this.chargeProducts();
  }

  private chargeProducts() {
    this.productManagementService.allProducts().then((allProducts) => {
      this.allProducts = allProducts;
    })
  }

  filterProduct(filter: string) {
    this.productManagementService.searchProduct(filter).then((allProducts) => {
      this.allProducts = allProducts;
    })
  }

  navigateToProductInformation(product: any) {
    const selectedProduct: PaneInfoWithId = product as PaneInfoWithId;
    this.router.navigate([{outlets: {right: ['information', selectedProduct.id]}}], {
      relativeTo: this.activatedRoute
    });
  }

  navigateToCreateProduct() {
    this.router.navigate([{outlets: {right: ['create']}}], {
      relativeTo: this.activatedRoute
    });
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
