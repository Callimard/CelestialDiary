import {Component, OnInit} from '@angular/core';
import {EquipmentDTO} from "../../../../data/company-management/equipment/equipment-dto";
import {Location} from "@angular/common";
import {ActivatedRoute} from "@angular/router";
import {PrivilegeService} from "../../../../service/authentication/privilege.service";
import {EquipmentManagementService} from "../../../../service/company-management/equipment/equipment-management.service";

@Component({
  selector: 'app-equipment-information',
  templateUrl: './equipment-information.component.html',
  styleUrls: ['./equipment-information.component.css']
})
export class EquipmentInformationComponent implements OnInit {

  equipment!: EquipmentDTO;

  constructor(private equipmentManagementService: EquipmentManagementService, private location: Location, private activatedRoute: ActivatedRoute,
              private privilegeService: PrivilegeService) {
    this.activatedRoute.params.subscribe({
      next: (param) => {
        const equipmentId: string | undefined = param["equipmentId"];
        if (equipmentId != null) {
          this.chargeEquipment(equipmentId);
        }
      }
    })
  }

  ngOnInit(): void {
    // Nothing
  }

  private chargeEquipment(equipmentId: string) {
    this.equipmentManagementService.getSpecificEquipment(equipmentId).then((equipment) => {
      this.equipment = equipment;
    })
  }

  goBack() {
    this.location.back();
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
