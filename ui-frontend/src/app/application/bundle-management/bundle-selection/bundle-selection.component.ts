import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {BundleManagementService} from "../../../../service/company-management/saleable/bundle-management.service";
import {WrappedBundleDTO} from "../../../../data/company-management/saleable/bundle/wrapped-bundle-dto";
import {PrivilegeService} from "../../../../service/authentication/privilege.service";
import {SaleablePaneInfoTransformer} from "../../utils/saleable-pane-info-transformer";
import {PaneInfoWithId} from "../../../library/informative/info-pane/info-pane.component";

@Component({
  selector: '[app-bundle-selection]',
  templateUrl: './bundle-selection.component.html',
  styleUrls: ['./bundle-selection.component.css']
})
export class BundleSelectionComponent implements OnInit {

  allBundles: WrappedBundleDTO[] = []

  saleablePaneInfoTransformer: SaleablePaneInfoTransformer = new SaleablePaneInfoTransformer();

  @Output() bundleSelected = new EventEmitter<string>();
  @Output() wantCreateBundle = new EventEmitter<boolean>();

  constructor(private bundleManagementService: BundleManagementService,
              private privilegeService: PrivilegeService) {
    // Nothing
  }

  ngOnInit(): void {
    this.chargeBundles();
  }

  public reload() {
    this.chargeBundles();
  }

  private chargeBundles() {
    this.bundleManagementService.allBundles().then((allBundles) => {
      this.allBundles = allBundles;
    })
  }

  filterBundle(filter: string) {
    this.bundleManagementService.searchBundle(filter).then((allBundles) => {
      this.allBundles = allBundles;
    })
  }

  selectBundle(bundle: any) {
    const selectedBundle: PaneInfoWithId = bundle as PaneInfoWithId;
    this.bundleSelected.emit(selectedBundle.id);
  }

  createBundle() {
    this.wantCreateBundle.emit(true);
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
