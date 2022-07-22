import {SaleableCreationInformation} from "../saleable-creation-information";

export interface BundleCreationInformation extends SaleableCreationInformation{
  prestations: string[],
  products: string[]
}
