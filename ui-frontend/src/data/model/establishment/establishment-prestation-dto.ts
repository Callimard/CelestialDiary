import {EstablishmentSaleableDTO} from "./establishment-saleable-dto";
import {WrappedPrestationDTO} from "../saleable/prestation/wrapped-prestation-dto";

export interface EstablishmentPrestationDTO extends EstablishmentSaleableDTO {
  prestation: WrappedPrestationDTO
}
