import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {EquipmentDTO} from "../../../../../../data/model/equipment/equipment-dto";
import {FormControl, FormGroup} from "@angular/forms";
import {EquipmentManagementService} from "../../../../../../service/company-management/equipment/equipment-management.service";
import {EquipmentUpdatedInformation} from "../../../../../../data/model/equipment/equipment-updated-information";

@Component({
  selector: '[app-equipment-form-updater]',
  templateUrl: './equipment-form-updater.component.html',
  styleUrls: ['./equipment-form-updater.component.css']
})
export class EquipmentFormUpdaterComponent implements OnInit {

  @Input() equipment!: EquipmentDTO;
  @Input() allDisabled = false;

  @Output() equipmentHasBeenUpdated = new EventEmitter<boolean>();

  updateFailed = false;

  equipmentUpdateForm = new FormGroup({});

  constructor(private equipmentManagementService: EquipmentManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    this.initEquipmentRoleUpdateForm();
  }

  private initEquipmentRoleUpdateForm() {
    this.equipmentUpdateForm = new FormGroup({
      name: new FormControl(this.equipment.name),
      description: new FormControl(this.equipment.description)
    });

    if (this.allDisabled) {
      this.equipmentUpdateForm.disable();
    }
  }

  onEquipmentUpdate() {
    const form = this.equipmentUpdateForm.value;
    const updates: EquipmentUpdatedInformation = {
      name: form.name,
      description: form.description
    }

    this.equipmentManagementService.updateEquipment(this.equipment.id, updates).then((equipment) => {
      this.updateFailed = false;
      this.equipment = equipment;
      this.equipmentHasBeenUpdated.emit(true);
      this.initEquipmentRoleUpdateForm();
    }).catch(() => {
      this.equipmentHasBeenUpdated.emit(false);
      this.updateFailed = true;
    })
  }
}
