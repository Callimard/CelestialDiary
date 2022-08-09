import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {WrappedPrestationDTO} from "../../../../data/company-management/saleable/prestation/wrapped-prestation-dto";
import {PrestationManagementService} from "../../../../service/company-management/saleable/prestation-management.service";
import {PrivilegeService} from "../../../../service/authentication/privilege.service";
import {PaneInfoTransformer, PaneInfoWithId} from "../../../library/informative/info-pane/info-pane.component";
import {WrappedSaleableDTO} from "../../../../data/company-management/saleable/wrapped-saleable-dto";
import {SaleablePaneInfoTransformer} from "../../utils/saleable-pane-info-transformer";

@Component({
  selector: '[app-prestation-selection]',
  templateUrl: './prestation-selection.component.html',
  styleUrls: ['./prestation-selection.component.css']
})
export class PrestationSelectionComponent implements OnInit {

  @Output() prestationSelected = new EventEmitter<string>();
  @Output() wantCreatePrestation = new EventEmitter<boolean>();

  allPrestations: WrappedPrestationDTO[] = []

  saleablePaneInfoTransformer: PaneInfoTransformer<WrappedSaleableDTO> = new SaleablePaneInfoTransformer();

  constructor(private prestationManagementService: PrestationManagementService,
              private privilegeService: PrivilegeService) {
    // Nothing
  }

  ngOnInit(): void {
    this.chargePrestations();
  }

  public reload() {
    this.chargePrestations();
  }

  private chargePrestations() {
    this.prestationManagementService.allPrestations().then((allPrestations) => {
      this.allPrestations = allPrestations;
    })
  }

  filterPrestation(filter: string) {
    this.prestationManagementService.searchPrestation(filter).then((allPrestations) => {
      this.allPrestations = allPrestations;
    })
  }

  selectPrestation(prestation: any) {
    const selectedPrestation: PaneInfoWithId = prestation as PaneInfoWithId;
    this.prestationSelected.emit(selectedPrestation.id);
  }

  createPrestation() {
    this.wantCreatePrestation.emit(true);
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
