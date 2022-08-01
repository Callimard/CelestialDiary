import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {PrestationManagementService} from "../../../../../service/company-management/saleable/prestation-management.service";
import {PrestationCreationInformation} from "../../../../../data/company-management/saleable/prestation/prestation-creation-information";
import {PrestationFormGroup} from "../../utils/prestation-form-group";
import {EquipmentManagementService} from "../../../../../service/company-management/equipment/equipment-management.service";

@Component({
  selector: '[app-prestation-form-creator]',
  templateUrl: './prestation-form-creator.component.html',
  styleUrls: ['./prestation-form-creator.component.css']
})
export class PrestationFormCreatorComponent implements OnInit {

  creationFailed = false;

  @Output() prestationCreated = new EventEmitter<boolean>();

  prestationCreatorForm: PrestationFormGroup;

  constructor(private prestationManagementService: PrestationManagementService, private equipmentManagementService: EquipmentManagementService) {
    this.prestationCreatorForm = new PrestationFormGroup(this.equipmentManagementService, true)
  }

  ngOnInit(): void {
    // Nothing
  }

  onPrestationCreation() {
    const form = this.prestationCreatorForm.value;
    const prestationInformation: PrestationCreationInformation = {
      name: form.name,
      description: form.description,
      suggestedPrice: form.suggestedPrice,
      nbNeededTechnician: form.nbNeededTechnician,
      nbClient: form.nbClient,
      suggestedExecutionTime: form.suggestedExecutionTime,
      neededEquipments: this.prestationCreatorForm.equipmentSelected
    }

        this.prestationManagementService.createPrestation(prestationInformation).then(() => {
          this.creationFailed = false;
          this.prestationCreated.emit(true);
        }).catch(() => {
          this.creationFailed = true;
        })
  }
}
