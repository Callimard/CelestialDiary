import {EquipmentDTO} from "../equipment/equipment-dto";
import {EstablishmentEquipmentDTO} from "./establishment-equipment-dto";

export interface AdvancedEstablishmentEquipmentContainerDTO {
  equipment: EquipmentDTO,
  establishmentEquipments: EstablishmentEquipmentDTO[]
}
