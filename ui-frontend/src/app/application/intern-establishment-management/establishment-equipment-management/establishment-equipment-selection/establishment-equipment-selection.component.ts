import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {EstablishmentEquipmentManagementService} from "../../../../../service/intern-establishment-management/equipment/establishment-equipment-management.service";
import {Observable} from "rxjs";
import {EstablishmentEquipmentDTO} from "../../../../../data/model/establishment/establishment-equipment-dto";
import {ActivatedRoute} from "@angular/router";
import {PaneInfo, PaneInfoTransformer} from "../../../../library/informative/info-pane/info-pane.component";
import {PrivilegeService} from "../../../../../service/authentication/privilege.service";

export class EstablishmentEquipmentPaneInfoTransformer implements PaneInfoTransformer<EstablishmentEquipmentDTO> {
  transform(elem: EstablishmentEquipmentDTO): EstablishmentEquipmentPaneInfo {
    return {
      title: elem.equipment.name,
      subTitle: elem.equipment.description,
      img: elem.equipment.photo,
      establishmentEquipment: elem
    }
  }
}

export interface EstablishmentEquipmentPaneInfo extends PaneInfo {
  establishmentEquipment: EstablishmentEquipmentDTO
}

@Component({
  selector: '[app-establishment-equipment-selection]',
  templateUrl: './establishment-equipment-selection.component.html',
  styleUrls: ['./establishment-equipment-selection.component.css']
})
export class EstablishmentEquipmentSelectionComponent implements OnInit {

  @Output() establishmentEquipmentSelected = new EventEmitter<EstablishmentEquipmentDTO>();
  @Output() wantAddEstablishmentEquipment = new EventEmitter<boolean>();

  establishmentId!: string;

  equipments$!: Observable<EstablishmentEquipmentDTO[]>;

  allEquipments: EstablishmentEquipmentDTO[] = [];

  transformer: EstablishmentEquipmentPaneInfoTransformer = new EstablishmentEquipmentPaneInfoTransformer();

  constructor(private establishmentEquipmentManagementService: EstablishmentEquipmentManagementService,
              private privilegeService: PrivilegeService,
              private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.establishmentId = params['establishmentId'];
      this.equipments$ = this.establishmentEquipmentManagementService.allEstablishmentEquipments(this.establishmentId);
      this.chargeEstablishmentEquipments();
    });
  }

  public reload() {
    this.chargeEstablishmentEquipments();
  }

  private chargeEstablishmentEquipments() {
    this.equipments$.subscribe((allEquipments) => {
      this.allEquipments = allEquipments;
    });
  }

  selectEstablishmentEquipment(equipment: EstablishmentEquipmentPaneInfo) {
    this.establishmentEquipmentSelected.emit(equipment.establishmentEquipment);
  }

  filterEstablishmentEquipments(filter: string) {
    this.establishmentEquipmentManagementService.searchEstablishmentEquipments(this.establishmentId, filter).subscribe((allEquipments) => {
      this.allEquipments = allEquipments;
    });
  }

  navigateToAddEstablishmentEquipment() {
    this.wantAddEstablishmentEquipment.emit(true);
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
