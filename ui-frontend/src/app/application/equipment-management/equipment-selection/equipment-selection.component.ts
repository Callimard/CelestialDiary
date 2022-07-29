import {Component, OnInit} from '@angular/core';
import {EquipmentDTO} from "../../../../data/company-management/equipment/equipment-dto";
import {ActivatedRoute, Router} from "@angular/router";
import {PrivilegeService} from "../../../../service/authentication/privilege.service";
import {EquipmentManagementService} from "../../../../service/company-management/equipment/equipment-management.service";

@Component({
  selector: 'app-equipment-selection',
  templateUrl: './equipment-selection.component.html',
  styleUrls: ['./equipment-selection.component.css']
})
export class EquipmentSelectionComponent implements OnInit {

  allEquipments: EquipmentDTO[] = [];

  constructor(private equipmentManagementService: EquipmentManagementService, private router: Router, private activatedRoute: ActivatedRoute,
              private privilegeService: PrivilegeService) {
    // Nothing
  }

  ngOnInit(): void {
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

  navigateToEquipmentInformation(role: any) {
    const selectedEquipment: EquipmentDTO = role as EquipmentDTO;
    this.router.navigate([{outlets: {right: ['information', selectedEquipment.id]}}], {
      relativeTo: this.activatedRoute
    });
  }

  navigateToCreateEquipment() {
    this.router.navigate([{outlets: {right: ['create']}}], {
      relativeTo: this.activatedRoute
    });
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
