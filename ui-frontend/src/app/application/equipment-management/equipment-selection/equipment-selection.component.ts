import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {EquipmentDTO} from "../../../../data/company-management/equipment/equipment-dto";
import {PrivilegeService} from "../../../../service/authentication/privilege.service";
import {EquipmentManagementService} from "../../../../service/company-management/equipment/equipment-management.service";
import {PaneInfoTransformer, PaneInfoWithId} from "../../../library/informative/info-pane/info-pane.component";

export class EquipmentPaneInfoTransformer implements PaneInfoTransformer<EquipmentDTO> {
  transform(equipment: EquipmentDTO): PaneInfoWithId {
    return {
      id: equipment.id,
      title: equipment.name,
      img: equipment.photo
    };
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

  equipmentPaneInfoTransformer: PaneInfoTransformer<EquipmentDTO> = new EquipmentPaneInfoTransformer();

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
