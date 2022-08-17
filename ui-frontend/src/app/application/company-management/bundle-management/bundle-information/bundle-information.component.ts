import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {BundleDTO} from "../../../../../data/model/saleable/bundle/bundle-dto";
import {BundleManagementService} from "../../../../../service/company-management/saleable/bundle-management.service";
import {PrivilegeService} from "../../../../../service/authentication/privilege.service";

@Component({
  selector: '[app-bundle-information]',
  templateUrl: './bundle-information.component.html',
  styleUrls: ['./bundle-information.component.css']
})
export class BundleInformationComponent implements OnInit, OnChanges {

  @Input() bundleId?: string;

  @Output() wantGoBack = new EventEmitter<boolean>();
  @Output() bundleHasBeenUpdated = new EventEmitter<boolean>();

  bundle?: BundleDTO;

  constructor(private bundleManagementService: BundleManagementService,
              private privilegeService: PrivilegeService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    if (this.bundleId != null)
      this.chargeBundle(this.bundleId);
  }

  private chargeBundle(bundleId: string) {
    this.bundleManagementService.getSpecificBundle(bundleId).then((bundle) => {
      this.bundle = bundle;
    })
  }

  goBack() {
    this.wantGoBack.emit(true);
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
