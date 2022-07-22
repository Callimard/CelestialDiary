import {Component, Input, OnInit} from '@angular/core';
import {PrestationDTO} from "../../../../../data/company-management/saleable/prestation/prestation-dto";
import {FormControl, FormGroup} from "@angular/forms";
import {PrestationUpdatedInformation} from "../../../../../data/company-management/saleable/prestation/prestation-updated-information";
import {PrestationManagementService} from "../../../../../service/company-management/prestation-management.service";

@Component({
  selector: '[app-prestation-form-updater]',
  templateUrl: './prestation-form-updater.component.html',
  styleUrls: ['./prestation-form-updater.component.css']
})
export class PrestationFormUpdaterComponent implements OnInit {

  @Input() prestation!: PrestationDTO

  updateFailed = false;

  prestationUpdateForm!: FormGroup

  constructor(private prestationManagementService: PrestationManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    this.prestationUpdateForm = new FormGroup({
      name: new FormControl(this.prestation.name),
      description: new FormControl(this.prestation.description),
      suggestedPrice: new FormControl(this.prestation.suggestedPrice),
      nbNeededTechnician: new FormControl(this.prestation.nbNeededTechnician),
      nbClient: new FormControl(this.prestation.nbClient),
      suggestedExecutionTime: new FormControl(this.prestation.suggestedExecutionTime)
    })
  }

  onPrestationUpdate() {
    const form = this.prestationUpdateForm.value;
    const prestationUpdates: PrestationUpdatedInformation = {
      name: form.name,
      description: form.description,
      suggestedPrice: form.suggestedPrice,
      nbNeededTechnician: form.nbNeededTechnician,
      nbClient: form.nbClient,
      suggestedExecutionTime: form.suggestedExecutionTime
    }
    this.prestationManagementService.updatePrestation(this.prestation.id, prestationUpdates).then((wrappedPrestation) => {
      this.prestation.name = wrappedPrestation.name;
      this.prestation.description = wrappedPrestation.description;
      this.prestation.suggestedPrice = wrappedPrestation.suggestedPrice;

      this.updateFailed = false;
    }).catch(() => {
      this.updateFailed = true;
    })
  }

}
