import {SaleableUpdatedInformation} from "../saleable-updated-information";

export interface PrestationUpdatedInformation extends SaleableUpdatedInformation {
  nbNeededTechnician?: number,
  nbClient?: number,
  suggestedExecutionTime?: number,
  neededEquipments?: string[]
}
