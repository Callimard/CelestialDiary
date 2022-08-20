import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {RoomCreationFormGroup} from "../../utils/room-creation-form-group";
import {RoomManagementService} from "../../../../../../service/intern-establishment-management/equipment/room-management.service";
import {RoomCreationInformation} from "../../../../../../data/model/establishment/room-creation-information";

@Component({
  selector: '[app-room-creation-form]',
  templateUrl: './room-creation-form.component.html',
  styleUrls: ['./room-creation-form.component.css']
})
export class RoomCreationFormComponent implements OnInit {

  @Input() establishmentId!: string;

  @Output() roomCreated = new EventEmitter();

  roomCreationFormGroup = new RoomCreationFormGroup()

  constructor(private roomManagementService: RoomManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  createRoom() {
    const roomCreationInformation: RoomCreationInformation = this.roomCreationFormGroup.toRoomCreationInformation();
    this.roomManagementService.createRoom(this.establishmentId, roomCreationInformation).subscribe(() => {
      this.roomCreated.emit();
      this.roomCreationFormGroup = new RoomCreationFormGroup();
    })
  }

}
