import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {PrestationDTO} from "../../../../../data/company-management/saleable/prestation/prestation-dto";
import {FormControl, FormGroup} from "@angular/forms";
import {PrestationUpdatedInformation} from "../../../../../data/company-management/saleable/prestation/prestation-updated-information";
import {PrestationManagementService} from "../../../../../service/company-management/saleable/prestation-management.service";

@Component({
  selector: '[app-prestation-form-updater]',
  templateUrl: './prestation-form-updater.component.html',
  styleUrls: ['./prestation-form-updater.component.css']
})
export class PrestationFormUpdaterComponent implements OnInit, OnChanges {

  @Input() prestation!: PrestationDTO
  @Input() allDisabled = false;

  updateFailed = false;

  prestationUpdateForm!: FormGroup

  constructor(private prestationManagementService: PrestationManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    this.initUpdaterForm();
  }

  private initUpdaterForm() {
    this.prestationUpdateForm = new FormGroup({
      name: new FormControl({value: this.prestation.name, disabled: this.allDisabled}),
      description: new FormControl({value: this.prestation.description, disabled: this.allDisabled}),
      suggestedPrice: new FormControl({value: this.prestation.suggestedPrice, disabled: this.allDisabled}),
      nbNeededTechnician: new FormControl({value: this.prestation.nbNeededTechnician, disabled: this.allDisabled}),
      nbClient: new FormControl({value: this.prestation.nbClient, disabled: this.allDisabled}),
      suggestedExecutionTime: new FormControl({value: this.prestation.suggestedExecutionTime, disabled: this.allDisabled})
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
      this.prestation.activated = wrappedPrestation.activated;

      this.updateFailed = false;
    }).catch(() => {
      this.updateFailed = true;
    })
  }

}
