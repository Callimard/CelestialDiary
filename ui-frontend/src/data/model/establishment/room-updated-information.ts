import {RoomEquipmentDTO} from "./room-equipment-dto";

export interface RoomUpdatedInformation {
  name: string,
  capacity: number,
  photo?: string,
  available: boolean,
  roomEquipments: RoomEquipmentDTO[]
}
