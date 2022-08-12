import {SaleableDTO} from "../saleable-dto";
import {EquipmentDTO} from "../../equipment/equipment-dto";

export interface PrestationDTO extends SaleableDTO {
  nbNeededTechnician: number,
  nbClient: number,
  suggestedExecutionTime: number,
  neededEquipments: EquipmentDTO[]
}
