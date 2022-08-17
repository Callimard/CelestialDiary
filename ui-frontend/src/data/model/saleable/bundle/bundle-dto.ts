import {SaleableDTO} from "../saleable-dto";
import {BundlePrestation} from "../prestation/bundle-prestation";
import {BundleProduct} from "../product/bundle-product";

export interface BundleDTO extends SaleableDTO {
  prestations?: BundlePrestation[],
  products?: BundleProduct[]
}
