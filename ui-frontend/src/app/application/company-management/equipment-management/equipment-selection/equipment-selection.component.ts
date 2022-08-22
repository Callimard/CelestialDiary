import {Component, EventEmitter, OnInit, Output, Pipe} from '@angular/core';
import {EquipmentDTO} from "../../../../../data/model/equipment/equipment-dto";
import {PrivilegeService} from "../../../../../service/authentication/privilege.service";
import {EquipmentManagementService} from "../../../../../service/company-management/equipment/equipment-management.service";
import {PaneInfoTransformer, PaneInfoWithId} from "../../../../library/informative/info-pane/info-pane.component";

@Pipe({name: "EquipmentPaneInfo"})
export class EquipmentPaneInfoPipe implements PaneInfoTransformer<EquipmentDTO> {
  transform(equipment: EquipmentDTO | undefined): PaneInfoWithId | undefined {
    return equipment != null ? {
      id: equipment.id,
      title: equipment.name,
      img: equipment.photo
    } : undefined;
  }

}

@Component({
  selector: '[app-equipment-selection]',
  templateUrl: './equipment-selection.component.html',
  styleUrls: ['./equipment-selection.component.css']
})
export class EquipmentSelectionComponent implements OnInit {

  @Output() equipmentSelected = new EventEmitter<string>();
  @Output() wantCreateEquipment = new EventEmitter<boolean>();

  allEquipments: EquipmentDTO[] = [];

  equipmentPaneInfoTransformer: PaneInfoTransformer<EquipmentDTO> = new EquipmentPaneInfoPipe();

  constructor(private equipmentManagementService: EquipmentManagementService,
              private privilegeService: PrivilegeService) {
    // Nothing
  }

  ngOnInit(): void {
    this.chargeEquipments();
  }

  public reload() {
    this.chargeEquipments();
  }

  private chargeEquipments() {
    this.equipmentManagementService.allEquipments().then((allEquipments) => {
      this.allEquipments = allEquipments;
    })
  }

  filterEquipments(filter: string) {
    this.equipmentManagementService.searchEquipment(filter).then((allEquipments) => {
      this.allEquipments = allEquipments;
    })
  }

  selectEquipment(equipment: any) {
    const selectedEquipment: PaneInfoWithId = equipment as PaneInfoWithId;
    this.equipmentSelected.emit(selectedEquipment.id);
  }

  navigateToCreateEquipment() {
    this.wantCreateEquipment.emit(true);
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
