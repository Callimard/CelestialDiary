import {FormControl, FormGroup, Validators} from "@angular/forms";
import {RoomDTO} from "../../../../../data/model/establishment/room-dto";
import {RoomUpdatedInformation} from "../../../../../data/model/establishment/room-updated-information";

export class RoomUpdateFormGroup extends FormGroup {

  constructor(room: RoomDTO) {
    super({
      name: new FormControl(room.name, [Validators.required]),
      capacity: new FormControl(room.capacity, [Validators.min(1)]),
      available: new FormControl(room.available)
    });
  }

  toRoomUpdatedInformation(): RoomUpdatedInformation {
    return {
      name: this.value.name,
      capacity: this.value.capacity,
      available: this.value.available
    }
  }
}
