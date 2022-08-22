import {RoomEquipmentDTO} from "./room-equipment-dto";

export interface RoomDTO {
  name: string,
  capacity: number,
  roomEquipments: RoomEquipmentDTO[]
  photo?: string,
  available: boolean
}
