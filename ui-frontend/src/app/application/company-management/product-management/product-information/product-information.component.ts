import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {ProductDTO} from "../../../../../data/model/saleable/product/product-dto";
import {ProductManagementService} from "../../../../../service/company-management/saleable/product-management.service";
import {PrivilegeService} from "../../../../../service/authentication/privilege.service";

@Component({
  selector: '[app-product-information]',
  templateUrl: './product-information.component.html',
  styleUrls: ['./product-information.component.css']
})
export class ProductInformationComponent implements OnInit, OnChanges {

  @Input() productId?: string;

  @Output() wantGoBack = new EventEmitter<boolean>();
  @Output() productHasBeenUpdated = new EventEmitter<boolean>();

  product?: ProductDTO;

  constructor(private productManagementService: ProductManagementService,
              private privilegeService: PrivilegeService) {
    // Nothing
  }

  ngOnInit(): void {
    // nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    if (this.productId != null) {
      this.chargeProduct(this.productId);
    }
  }


  private chargeProduct(productId: string) {
    this.productManagementService.getSpecificProduct(productId).then((product) => {
      this.product = product;
    })
  }

  goBack() {
    this.wantGoBack.emit(true);
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
