import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {WrappedProductDTO} from "../../../../../data/model/saleable/product/wrapped-product-dto";
import {ProductManagementService} from "../../../../../service/company-management/saleable/product-management.service";
import {PrivilegeService} from "../../../../../service/authentication/privilege.service";
import {PaneInfoTransformer, PaneInfoWithId} from "../../../../library/informative/info-pane/info-pane.component";
import {WrappedSaleableDTO} from "../../../../../data/model/saleable/wrapped-saleable-dto";
import {SaleablePaneInfoTransformer} from "../../utils/saleable-pane-info-transformer";

@Component({
  selector: '[app-product-selection]',
  templateUrl: './product-selection.component.html',
  styleUrls: ['./product-selection.component.css']
})
export class ProductSelectionComponent implements OnInit {

  @Output() productSelected = new EventEmitter<string>();
  @Output() wantCreateProduct = new EventEmitter<boolean>();

  allProducts: WrappedProductDTO[] = [];

  saleablePaneInfoTransformer: PaneInfoTransformer<WrappedSaleableDTO> = new SaleablePaneInfoTransformer();

  constructor(private productManagementService: ProductManagementService,
              private privilegeService: PrivilegeService) {
    // Nothing
  }

  ngOnInit(): void {
    this.chargeProducts();
  }

  public reload() {
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

  selectProduct(product: any) {
    const selectedProduct: PaneInfoWithId = product as PaneInfoWithId;
    this.productSelected.emit(selectedProduct.id);
  }

  createProduct() {
    this.wantCreateProduct.emit(true);
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
