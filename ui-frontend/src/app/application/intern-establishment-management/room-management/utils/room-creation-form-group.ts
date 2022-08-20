import {FormControl, FormGroup, Validators} from "@angular/forms";
import {RoomCreationInformation} from "../../../../../data/model/establishment/room-creation-information";

export class RoomCreationFormGroup extends FormGroup {
  constructor() {
    super({
      name: new FormControl('', [Validators.required]),
      capacity: new FormControl(1, [Validators.min(1)])
    });
  }

  public toRoomCreationInformation(): RoomCreationInformation {
    return {
      name: this.value.name,
      capacity: this.value.capacity
    }
  }
}
