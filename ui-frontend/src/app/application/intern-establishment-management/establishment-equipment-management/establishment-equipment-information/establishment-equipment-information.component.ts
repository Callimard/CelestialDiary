import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {EstablishmentEquipmentDTO} from "../../../../../data/model/establishment/establishment-equipment-dto";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {EstablishmentEquipmentManagementService} from "../../../../../service/intern-establishment-management/equipment/establishment-equipment-management.service";
import {PrivilegeService} from "../../../../../service/authentication/privilege.service";
import {EstablishmentEquipmentUpdatedInformation} from "../../../../../data/model/establishment/establishment-equipment-updated-information";

@Component({
  selector: '[app-establishment-equipment-information]',
  templateUrl: './establishment-equipment-information.component.html',
  styleUrls: ['./establishment-equipment-information.component.css']
})
export class EstablishmentEquipmentInformationComponent implements OnInit, OnChanges {

  @Input() establishmentId!: string
  @Input() establishmentEquipment!: EstablishmentEquipmentDTO;

  @Output() wantGoBack = new EventEmitter<boolean>();
  @Output() establishmentEquipmentHasBeenUpdated = new EventEmitter<boolean>();
  @Output() establishmentEquipmentHasBeenDeleted = new EventEmitter<boolean>();

  updateEquipmentForm?: FormGroup;

  constructor(private establishmentEquipmentManagementService: EstablishmentEquipmentManagementService,
              private privilegeService: PrivilegeService) {
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    this.reload();
  }

  public reload() {
    this.updateEquipmentForm = new FormGroup({
      name: new FormControl(this.establishmentEquipment.name, [Validators.required]),
      available: new FormControl(this.establishmentEquipment.available)
    })
  }

  onUpdate() {
    const form = this.updateEquipmentForm?.value;
    const updates: EstablishmentEquipmentUpdatedInformation = {
      name: form.name,
      available: form.available
    }

    this.establishmentEquipmentManagementService.updateEstablishmentEquipment(this.establishmentId, this.establishmentEquipment.id, updates)
      .subscribe((establishmentEquipment) => {
        this.establishmentEquipment = establishmentEquipment;
        this.reload();
        this.establishmentEquipmentHasBeenUpdated.emit(true);
      });
  }

  deleteEstablishmentEquipment() {
    this.establishmentEquipmentManagementService.deleteEstablishmentEquipment(this.establishmentId, this.establishmentEquipment.id)
      .subscribe(() => {
        this.establishmentEquipmentHasBeenDeleted.emit(true);
        this.wantGoBack.emit(true);
      })
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
