import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {RoomDTO} from "../../../../../data/model/establishment/room-dto";
import {PrivilegeService} from "../../../../../service/authentication/privilege.service";
import {RoomManagementService} from "../../../../../service/intern-establishment-management/equipment/room-management.service";
import {PaneInfoTransformer, PaneInfoWithId} from "../../../../library/informative/info-pane/info-pane.component";


export interface RoomPaneInfo extends PaneInfoWithId {
  room: RoomDTO
}

export class RoomPaneInfoTransformer implements PaneInfoTransformer<RoomDTO> {

  transform(room: RoomDTO): RoomPaneInfo {
    return {
      id: room.name,
      title: room.name,
      room: room
    };
  }

}

@Component({
  selector: '[app-room-selection]',
  templateUrl: './room-selection.component.html',
  styleUrls: ['./room-selection.component.css']
})
export class RoomSelectionComponent implements OnInit, OnChanges {

  @Input() establishmentId!: string;

  @Output() roomSelected = new EventEmitter<RoomDTO>();
  @Output() wantCreateRoom = new EventEmitter();

  allRooms: RoomDTO[] = [];

  transformer = new RoomPaneInfoTransformer();

  constructor(private roomManagementService: RoomManagementService,
              private privilegeService: PrivilegeService) {
    // Noting
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    this.chargeRooms();
  }

  public reload() {
    this.chargeRooms();
  }

  private chargeRooms() {
    this.roomManagementService.allRooms(this.establishmentId).subscribe((allRooms) => this.allRooms = allRooms);
  }

  filterRooms(filter: string) {
    this.roomManagementService.searchRoom(this.establishmentId, filter).subscribe((allRooms) => this.allRooms = allRooms);
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
