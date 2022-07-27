import {SaleableCreationInformation} from "../saleable-creation-information";

export interface BundleCreationInformation extends SaleableCreationInformation {
  prestations: { [id: string]: number },
  products: { [id: string]: number }
}
