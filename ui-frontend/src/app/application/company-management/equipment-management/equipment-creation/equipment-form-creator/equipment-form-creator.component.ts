import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {EquipmentManagementService} from "../../../../../../service/company-management/equipment/equipment-management.service";
import {EquipmentCreationInformation} from "../../../../../../data/model/equipment/equipment-creation-information";

@Component({
  selector: '[app-equipment-form-creator]',
  templateUrl: './equipment-form-creator.component.html',
  styleUrls: ['./equipment-form-creator.component.css']
})
export class EquipmentFormCreatorComponent implements OnInit {

  creationFailed = false;

  @Output() equipmentCreated = new EventEmitter<boolean>();

  equipmentCreationForm = new FormGroup({
    name: new FormControl(null, [Validators.required]),
    description: new FormControl(null)
  })

  constructor(private equipmentManagementService: EquipmentManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  onEquipmentCreation() {
    const form = this.equipmentCreationForm.value;
    const equipmentInformation: EquipmentCreationInformation = {
      name: form.name,
      description: form.description
    }

    this.equipmentManagementService.createEquipment(equipmentInformation).then(() => {
      this.creationFailed = false;
      this.equipmentCreated.emit(true);
    }).catch(() => {
      this.creationFailed = true;
    })
  }
}
