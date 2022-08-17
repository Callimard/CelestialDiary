import {WrappedSaleableDTO} from "../wrapped-saleable-dto";

export interface WrappedPrestationDTO extends WrappedSaleableDTO {

  nbNeededTechnician: number,
  nbClient: number,
  suggestedExecutionTime: number,
}
