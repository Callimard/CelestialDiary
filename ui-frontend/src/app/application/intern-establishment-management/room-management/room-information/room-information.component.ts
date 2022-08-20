import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {RoomDTO} from "../../../../../data/model/establishment/room-dto";
import {RoomUpdateFormGroup} from "../utils/room-update-form-group";
import {PrivilegeService} from "../../../../../service/authentication/privilege.service";
import {RoomManagementService} from "../../../../../service/intern-establishment-management/equipment/room-management.service";
import {RoomUpdatedInformation} from "../../../../../data/model/establishment/room-updated-information";

@Component({
  selector: '[app-room-information]',
  templateUrl: './room-information.component.html',
  styleUrls: ['./room-information.component.css']
})
export class RoomInformationComponent implements OnInit, OnChanges {

  @Input() establishmentId!: string
  @Input() room!: RoomDTO;

  @Output() roomHasBeenUpdated = new EventEmitter();
  @Output() roomHasBeenDeleted = new EventEmitter();
  @Output() wantGoBack = new EventEmitter();

  roomUpdateFormGroup!: RoomUpdateFormGroup;

  constructor(private roomManagementService: RoomManagementService,
              private privilegeService: PrivilegeService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    this.roomUpdateFormGroup = new RoomUpdateFormGroup(this.room);
  }

  updateRoom() {
    const roomUpdatedInformation: RoomUpdatedInformation = this.roomUpdateFormGroup.toRoomUpdatedInformation();
    this.roomManagementService.updateRoom(this.establishmentId, this.room.name, roomUpdatedInformation).subscribe((room) => {
      this.room = room;
      this.roomUpdateFormGroup = new RoomUpdateFormGroup(this.room);
      this.roomHasBeenUpdated.emit();
    })
  }

  deleteRoom() {
    this.roomManagementService.deleteRoom(this.establishmentId, this.room.name).subscribe(() => {
      this.roomHasBeenDeleted.emit();
    })
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }

}
