import {SaleableDTO} from "../saleable-dto";

export interface PrestationDTO extends SaleableDTO {
  nbNeededTechnician: number,
  nbClient: number,
  suggestedExecutionTime: number
}
