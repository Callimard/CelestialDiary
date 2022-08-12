import {EstablishmentSaleableDTO} from "./establishment-saleable-dto";
import {WrappedBundleDTO} from "../saleable/bundle/wrapped-bundle-dto";

export interface EstablishmentBundleDTO extends EstablishmentSaleableDTO {
  bundle: WrappedBundleDTO
}
