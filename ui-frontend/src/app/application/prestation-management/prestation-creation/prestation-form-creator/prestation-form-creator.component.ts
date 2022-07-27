import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {PrestationManagementService} from "../../../../../service/company-management/saleable/prestation-management.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {PrestationCreationInformation} from "../../../../../data/company-management/saleable/prestation/prestation-creation-information";

@Component({
  selector: '[app-prestation-form-creator]',
  templateUrl: './prestation-form-creator.component.html',
  styleUrls: ['./prestation-form-creator.component.css']
})
export class PrestationFormCreatorComponent implements OnInit {

  creationFailed = false;

  @Output() prestationCreated = new EventEmitter<boolean>();

  prestationCreatorForm = new FormGroup({
    name: new FormControl(null, [Validators.required]),
    description: new FormControl(null),
    suggestedPrice: new FormControl(null, [Validators.required, Validators.min(0.01)]),
    nbNeededTechnician: new FormControl(null, [Validators.required, Validators.min(1)]),
    nbClient: new FormControl(null, [Validators.required, Validators.min(1)]),
    suggestedExecutionTime: new FormControl(null, [Validators.required, Validators.min(1)])
  })

  constructor(private prestationManagementService: PrestationManagementService) {
    // Nothing
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
      suggestedExecutionTime: form.suggestedExecutionTime
    }

    this.prestationManagementService.createPrestation(prestationInformation).then(() => {
      this.creationFailed = false;
      this.prestationCreated.emit(true);
    }).catch(() => {
      this.creationFailed = true;
    })
  }
}
