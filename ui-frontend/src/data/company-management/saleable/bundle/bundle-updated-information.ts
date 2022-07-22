import {SaleableUpdatedInformation} from "../saleable-updated-information";

export interface BundleUpdatedInformation extends SaleableUpdatedInformation{
  prestations?: string[],
  products?: string[]
}
