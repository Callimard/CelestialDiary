import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {EstablishmentEquipmentDTO} from "../../../../../data/model/establishment/establishment-equipment-dto";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {EstablishmentEquipmentManagementService} from "../../../../../service/intern-establishment-management/equipment/establishment-equipment-management.service";
import {ActivatedRoute} from "@angular/router";
import {EstablishmentEquipmentUpdatedInformation} from "../../../../../data/model/establishment/establishment-equipment-updated-information";
import {PrivilegeService} from "../../../../../service/authentication/privilege.service";

@Component({
  selector: '[app-establishment-equipment-information]',
  templateUrl: './establishment-equipment-information.component.html',
  styleUrls: ['./establishment-equipment-information.component.css']
})
export class EstablishmentEquipmentInformationComponent implements OnInit, OnChanges {

  @Input() establishmentEquipment?: EstablishmentEquipmentDTO;

  @Output() wantGoBack = new EventEmitter<boolean>();
  @Output() establishmentEquipmentHasBeenUpdated = new EventEmitter<boolean>();
  @Output() establishmentEquipmentHasBeenDeleted = new EventEmitter<boolean>();

  updateEquipmentForm?: FormGroup;

  establishmentId!: string

  constructor(private establishmentEquipmentManagementService: EstablishmentEquipmentManagementService,
              private privilegeService: PrivilegeService,
              private activatedRoute: ActivatedRoute) {
    this.activatedRoute.params.subscribe((params) => {
      this.establishmentId = params['establishmentId'];
    });
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    this.reload();
  }

  public reload() {
    if (this.establishmentEquipment != null)
      this.updateEquipmentForm = new FormGroup({
        quantity: new FormControl(this.establishmentEquipment.quantity, [Validators.min(1)]),
        numberUnusable: new FormControl(this.establishmentEquipment.numberUnusable, [Validators.min(0)])
      })
  }

  onUpdate() {
    const form = this.updateEquipmentForm?.value;
    const updates: EstablishmentEquipmentUpdatedInformation = {
      quantity: form.quantity,
      numberUnusable: form.numberUnusable
    }

    if (this.establishmentEquipment)
      this.establishmentEquipmentManagementService.updateEstablishmentEquipment(this.establishmentId, this.establishmentEquipment.equipment.id, updates)
        .subscribe((establishmentEquipment) => {
          this.establishmentEquipment = establishmentEquipment;
          this.reload();
          this.establishmentEquipmentHasBeenUpdated.emit(true);
        })
  }

  deleteEstablishmentEquipment() {
    if (this.establishmentEquipment)
      this.establishmentEquipmentManagementService.deleteEstablishmentEquipment(this.establishmentId, this.establishmentEquipment.equipment.id)
        .subscribe(() => {
          this.establishmentEquipment = undefined;
          this.establishmentEquipmentHasBeenDeleted.emit(true);
        })
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
