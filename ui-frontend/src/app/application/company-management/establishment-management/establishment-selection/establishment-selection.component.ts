import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {WrappedEstablishmentDTO} from "../../../../../data/model/establishment/wrapped-establishment-dto";
import {EstablishmentManagementService} from "../../../../../service/company-management/establishment/establishment-management.service";
import {PrivilegeService} from "../../../../../service/authentication/privilege.service";
import {PaneInfoTransformer, PaneInfoWithId} from "../../../../library/informative/info-pane/info-pane.component";

export class EstablishmentPaneInfoTransformer implements PaneInfoTransformer<WrappedEstablishmentDTO> {
  transform(establishment: WrappedEstablishmentDTO): PaneInfoWithId {
    return {
      id: establishment.id,
      title: establishment.name,
      subTitle: establishment.description,
      img: establishment.photo
    };
  }

}

@Component({
  selector: '[app-establishment-selection]',
  templateUrl: './establishment-selection.component.html',
  styleUrls: ['./establishment-selection.component.css']
})
export class EstablishmentSelectionComponent implements OnInit {

  @Output() establishmentSelected = new EventEmitter<string>();
  @Output() wantCreateEstablishment = new EventEmitter<boolean>();

  allEstablishments: WrappedEstablishmentDTO[] = [];

  establishmentPaneInfoTransformer: PaneInfoTransformer<WrappedEstablishmentDTO> = new EstablishmentPaneInfoTransformer();

  constructor(private establishmentManagementService: EstablishmentManagementService,
              private privilegeService: PrivilegeService) {
    // Nothing
  }

  ngOnInit(): void {
    this.chargeEstablishments();
  }

  public reload() {
    this.chargeEstablishments();
  }

  private chargeEstablishments() {
    this.establishmentManagementService.allEstablishments().then((allEstablishments) => {
      this.allEstablishments = allEstablishments;
    });
  }

  filterEstablishment(filter: string) {
    this.establishmentManagementService.searchEstablishment(filter).then((allEstablishments) => {
      this.allEstablishments = allEstablishments;
    });
  }

  selectEstablishment(establishment: any) {
    const selectedEstablishment: PaneInfoWithId = establishment as PaneInfoWithId;
    this.establishmentSelected.emit(selectedEstablishment.id);
  }

  createEstablishment() {
    this.wantCreateEstablishment.emit(true);
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
