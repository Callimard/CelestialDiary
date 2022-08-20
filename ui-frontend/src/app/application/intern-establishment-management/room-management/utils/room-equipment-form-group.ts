import {FormControl, FormGroup} from "@angular/forms";

export class RoomEquipmentFormGroup extends FormGroup {

  constructor() {
    super({
      weight: new FormControl(),
      establishmentEquipmentId: new FormControl()
    });
  }
}
