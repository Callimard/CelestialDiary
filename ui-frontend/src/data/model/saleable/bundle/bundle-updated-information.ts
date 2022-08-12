import {SaleableUpdatedInformation} from "../saleable-updated-information";

export interface BundleUpdatedInformation extends SaleableUpdatedInformation{
  prestations?: { [id: string]: number },
  products?: { [id: string]: number }
}
