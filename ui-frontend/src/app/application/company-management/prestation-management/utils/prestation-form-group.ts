import {FormControl, FormGroup, Validators} from "@angular/forms";
import {EquipmentDTO} from "../../../../../data/model/equipment/equipment-dto";
import {EquipmentManagementService} from "../../../../../service/company-management/equipment/equipment-management.service";
import {PrestationDTO} from "../../../../../data/model/saleable/prestation/prestation-dto";

export class PrestationFormGroup extends FormGroup {

  private _enabledEquipments: EquipmentDTO[] = [];

  constructor(private equipmentManagementService: EquipmentManagementService, withValidator = false, prestation?: PrestationDTO) {
    super({
      name: new FormControl(prestation?.name, withValidator ? [Validators.required] : []),
      description: new FormControl(prestation?.description),
      suggestedPrice: new FormControl(prestation?.suggestedPrice, withValidator ? [Validators.required, Validators.min(0.01)] : []),
      nbNeededTechnician: new FormControl(prestation?.nbNeededTechnician, withValidator ? [Validators.required, Validators.min(1)] : []),
      nbClient: new FormControl(prestation?.nbClient, withValidator ? [Validators.required, Validators.min(1)] : []),
      suggestedExecutionTime: new FormControl(prestation?.suggestedExecutionTime, withValidator ? [Validators.required, Validators.min(1)] : []),
      neededEquipments: new FormGroup({})
    });

    this.chargeEquipments(prestation?.neededEquipments);
  }

  private chargeEquipments(neededEquipments?: EquipmentDTO[]) {
    this.equipmentManagementService.allEquipments().then((allEquipments) => {
      this._enabledEquipments = allEquipments;

      for (let equipment of this._enabledEquipments) {
        this.neededEquipmentsFormGroup.addControl(equipment.id, new FormControl(false));
      }

      if (neededEquipments != null) {
        for (let equipment of neededEquipments) {
          this.neededEquipmentsFormGroup.setControl(equipment.id, new FormControl(true));
        }
      }
    })
  }

  get neededEquipmentsFormGroup(): FormGroup {
    return this.get('neededEquipments') as FormGroup;
  }

  get enabledEquipments(): EquipmentDTO[] {
    return this._enabledEquipments;
  }

  get equipmentSelected(): string[] {
    let selectedEquipment: string[] = [];
    const equipmentIds: string[] = Object.keys(this.neededEquipmentsFormGroup.controls);
    const values = this.neededEquipmentsFormGroup.value;
    for (let id of equipmentIds) {
      const selected: boolean = values[id];
      if (selected) {
        selectedEquipment.push(id);
      }
    }

    return selectedEquipment;
  }
}
