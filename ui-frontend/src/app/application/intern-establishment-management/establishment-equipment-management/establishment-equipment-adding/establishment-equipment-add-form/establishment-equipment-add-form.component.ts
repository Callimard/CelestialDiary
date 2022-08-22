import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {EquipmentManagementService} from "../../../../../../service/company-management/equipment/equipment-management.service";
import {EquipmentDTO} from "../../../../../../data/model/equipment/equipment-dto";
import {PaneInfoWithId} from "../../../../../library/informative/info-pane/info-pane.component";
import {EquipmentPaneInfoPipe} from "../../../../company-management/equipment-management/equipment-selection/equipment-selection.component";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {EstablishmentEquipmentManagementService} from "../../../../../../service/intern-establishment-management/equipment/establishment-equipment-management.service";
import {EstablishmentEquipmentAddingInformation} from "../../../../../../data/model/establishment/establishment-equipment-adding-information";

@Component({
  selector: '[app-establishment-equipment-add-form]',
  templateUrl: './establishment-equipment-add-form.component.html',
  styleUrls: ['./establishment-equipment-add-form.component.css']
})
export class EstablishmentEquipmentAddFormComponent implements OnInit {

  @Input() establishmentId!: string;

  @Output() establishmentEquipmentCreated = new EventEmitter<boolean>();

  companyEquipments: EquipmentDTO[] = [];
  availableEquipments: EquipmentDTO[] = [];

  equipmentPaneInfoTransformer = new EquipmentPaneInfoPipe();

  selectedEquipment?: EquipmentDTO;

  addEstablishmentEquipmentForm: FormGroup = new FormGroup({
    equipmentId: new FormControl(null, [Validators.required]),
    quantity: new FormControl(1, [Validators.required, Validators.min(1)])
  })

  constructor(private establishmentEquipmentManagementService: EstablishmentEquipmentManagementService,
              private equipmentManagementService: EquipmentManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    this.reload();
  }

  public reload() {
    this.equipmentManagementService.allEquipments().then((companyEquipments) => {
      this.companyEquipments = companyEquipments;
      this.fillAvailableEquipment();
    })
  }

  private fillAvailableEquipment() {
    this.availableEquipments = [];
    for (let equipment of this.companyEquipments) {
      this.availableEquipments.push(equipment);
    }
  }

  equipmentSelected(equipmentInfo: PaneInfoWithId) {
    this.replaceSelectedEquipmentInAvailableEquipments();

    for (let e of this.availableEquipments) {
      if (e.id === equipmentInfo.id) {
        this.selectedEquipment = e;
      }
    }

    if (this.selectedEquipment != null) {
      this.availableEquipments.splice(this.availableEquipments.indexOf(this.selectedEquipment), 1);

      this.addEstablishmentEquipmentForm.setControl('equipmentId', new FormControl(this.selectedEquipment.id));
    }
  }

  private replaceSelectedEquipmentInAvailableEquipments() {
    if (this.selectedEquipment != null) {
      this.availableEquipments.push(this.selectedEquipment);
      this.selectedEquipment = undefined;
    }
  }

  onAdd() {
    const addingInformation: EstablishmentEquipmentAddingInformation = {
      quantity: this.quantityFormControl.value
    }

    this.establishmentEquipmentManagementService.addEstablishmentEquipment(this.establishmentId, this.equipmentIdFormControl.value, addingInformation)
      .subscribe(() => {
        this.establishmentEquipmentCreated.emit(true);
        this.selectedEquipment = undefined;
        this.reload();
      });
  }

  get equipmentIdFormControl(): FormControl {
    return this.addEstablishmentEquipmentForm.get('equipmentId') as FormControl;
  }

  get quantityFormControl(): FormControl {
    return this.addEstablishmentEquipmentForm.get('quantity') as FormControl;
  }
}
