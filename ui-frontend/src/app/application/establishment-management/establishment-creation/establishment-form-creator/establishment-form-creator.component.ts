import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {EstablishmentManagementService} from "../../../../../service/company-management/establishment/establishment-management.service";
import {EstablishmentCreationInformation} from "../../../../../data/company-management/establishment/establishment-creation-information";
import {EstablishmentFormGroup} from "../../utils/establishment-form-group";


@Component({
  selector: '[app-establishment-form-creator]',
  templateUrl: './establishment-form-creator.component.html',
  styleUrls: ['./establishment-form-creator.component.css']
})
export class EstablishmentFormCreatorComponent implements OnInit {

  creationFailed = false;

  @Output() establishmentCreated = new EventEmitter<boolean>();

  establishmentCreationForm = new EstablishmentFormGroup(true);

  constructor(private establishmentManagementService: EstablishmentManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  onEstablishmentCreation() {
    const form = this.establishmentCreationForm.value;
    const establishmentInfo: EstablishmentCreationInformation = {
      name: form.name,
      description: form.description,
      address: form.address,
      mondayOpening: this.establishmentCreationForm.mondayOpenings.extractNonDatedTimeIntervalListDTO(),
      tuesdayOpening: this.establishmentCreationForm.tuesdayOpenings.extractNonDatedTimeIntervalListDTO(),
      wednesdayOpening: this.establishmentCreationForm.wednesdayOpenings.extractNonDatedTimeIntervalListDTO(),
      thursdayOpening: this.establishmentCreationForm.thursdayOpenings.extractNonDatedTimeIntervalListDTO(),
      fridayOpening: this.establishmentCreationForm.fridayOpenings.extractNonDatedTimeIntervalListDTO(),
      saturdayOpening: this.establishmentCreationForm.saturdayOpenings.extractNonDatedTimeIntervalListDTO(),
      sundayOpening: this.establishmentCreationForm.sundayOpenings.extractNonDatedTimeIntervalListDTO()
    }

    this.establishmentManagementService.createEstablishment(establishmentInfo).then(() => {
      this.creationFailed = false;
      this.establishmentCreated.emit(true);
    }).catch(() => {
      this.creationFailed = true;
      this.establishmentCreated.emit(false);
    })
  }

}
