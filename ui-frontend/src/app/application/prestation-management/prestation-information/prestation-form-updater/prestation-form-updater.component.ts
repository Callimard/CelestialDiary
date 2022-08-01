import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {PrestationDTO} from "../../../../../data/company-management/saleable/prestation/prestation-dto";
import {PrestationUpdatedInformation} from "../../../../../data/company-management/saleable/prestation/prestation-updated-information";
import {PrestationManagementService} from "../../../../../service/company-management/saleable/prestation-management.service";
import {PrestationFormGroup} from "../../utils/prestation-form-group";
import {EquipmentManagementService} from "../../../../../service/company-management/equipment/equipment-management.service";

@Component({
  selector: '[app-prestation-form-updater]',
  templateUrl: './prestation-form-updater.component.html',
  styleUrls: ['./prestation-form-updater.component.css']
})
export class PrestationFormUpdaterComponent implements OnInit, OnChanges {

  @Input() prestation!: PrestationDTO
  @Input() allDisabled = false;

  updateFailed = false;

  prestationUpdateForm!: PrestationFormGroup

  constructor(private prestationManagementService: PrestationManagementService, private equipmentManagementService: EquipmentManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    this.initUpdaterForm();
  }

  private initUpdaterForm() {
    this.prestationUpdateForm = new PrestationFormGroup(this.equipmentManagementService, false, this.prestation);

    if (this.allDisabled) {
      this.prestationUpdateForm.disable();
    }
  }

  onPrestationUpdate() {
    const form = this.prestationUpdateForm.value;
    const prestationUpdates: PrestationUpdatedInformation = {
      name: form.name,
      description: form.description,
      suggestedPrice: form.suggestedPrice,
      nbNeededTechnician: form.nbNeededTechnician,
      nbClient: form.nbClient,
      suggestedExecutionTime: form.suggestedExecutionTime,
      neededEquipments: this.prestationUpdateForm.equipmentSelected
    }

    this.prestationManagementService.updatePrestation(this.prestation.id, prestationUpdates).then((wrappedPrestation) => {
      this.prestation.name = wrappedPrestation.name;
      this.prestation.description = wrappedPrestation.description;
      this.prestation.suggestedPrice = wrappedPrestation.suggestedPrice;
      this.prestation.activated = wrappedPrestation.activated;

      this.updateFailed = false;
    }).catch(() => {
      this.updateFailed = true;
    })
  }

}
