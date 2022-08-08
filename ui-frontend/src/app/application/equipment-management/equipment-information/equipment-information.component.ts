import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {EquipmentDTO} from "../../../../data/company-management/equipment/equipment-dto";
import {PrivilegeService} from "../../../../service/authentication/privilege.service";
import {EquipmentManagementService} from "../../../../service/company-management/equipment/equipment-management.service";

@Component({
  selector: '[app-equipment-information]',
  templateUrl: './equipment-information.component.html',
  styleUrls: ['./equipment-information.component.css']
})
export class EquipmentInformationComponent implements OnInit, OnChanges {

  @Input() equipmentId?: string;

  @Output() wantGoBack = new EventEmitter<boolean>();
  @Output() equipmentHasBeenUpdated = new EventEmitter<boolean>();

  equipment!: EquipmentDTO;

  constructor(private equipmentManagementService: EquipmentManagementService,
              private privilegeService: PrivilegeService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    if (this.equipmentId != null)
      this.chargeEquipment(this.equipmentId);
  }

  private chargeEquipment(equipmentId: string) {
    this.equipmentManagementService.getSpecificEquipment(equipmentId).then((equipment) => {
      this.equipment = equipment;
    })
  }

  goBack() {
    this.wantGoBack.emit(true);
  }

  deleteEquipment() {
    this.equipmentManagementService.deleteEquipment(this.equipment.id).then(() => {
      this.goBack();
    })
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
