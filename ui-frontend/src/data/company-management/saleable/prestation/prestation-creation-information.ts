import {SaleableCreationInformation} from "../saleable-creation-information";

export interface PrestationCreationInformation extends SaleableCreationInformation {
  nbNeededTechnician: number,
  nbClient: number,
  suggestedExecutionTime: number
}
