import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {EstablishmentDTO} from "../../../../../../data/model/establishment/establishment-dto";
import {EstablishmentManagementService} from "../../../../../../service/company-management/establishment/establishment-management.service";
import {EstablishmentUpdatedInformation} from "../../../../../../data/model/establishment/establishment-updated-information";
import {EstablishmentFormGroup} from "../../utils/establishment-form-group";

@Component({
  selector: '[app-establishment-form-updater]',
  templateUrl: './establishment-form-updater.component.html',
  styleUrls: ['./establishment-form-updater.component.css']
})
export class EstablishmentFormUpdaterComponent implements OnInit, OnChanges {

  @Input() establishment!: EstablishmentDTO;
  @Input() allDisabled = false;

  @Output() hasBeenUpdated = new EventEmitter<boolean>();

  updateFailed = false;

  establishmentUpdateForm!: EstablishmentFormGroup;

  constructor(private establishmentManagerService: EstablishmentManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    this.initializeEstablishmentFormGroup();
  }

  private initializeEstablishmentFormGroup() {
    this.establishmentUpdateForm = new EstablishmentFormGroup(false, this.establishment);
  }

  onUpdate() {
    const establishmentUpdates: EstablishmentUpdatedInformation = this.establishmentUpdateForm.extractEstablishmentUpdatedInformation();

    this.establishmentManagerService.updateEstablishment(this.establishment.id, establishmentUpdates).then((wrappedEstablishment) => {
      this.establishment.name = wrappedEstablishment.name;
      this.establishment.description = wrappedEstablishment.description;
      this.establishment.address = wrappedEstablishment.address;
      this.establishment.activated = wrappedEstablishment.activated;
      this.updateFailed = false;
      this.hasBeenUpdated.emit(true);
    }).catch(() => {
      this.updateFailed = true;
      this.hasBeenUpdated.emit(false);
    });
  }
}
