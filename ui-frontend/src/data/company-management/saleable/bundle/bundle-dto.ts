import {SaleableDTO} from "../saleable-dto";
import {WrappedPrestationDTO} from "../prestation/wrapped-prestation-dto";
import {WrappedProductDTO} from "../product/wrapped-product-dto";

export interface BundleDTO extends SaleableDTO {
  prestations?: WrappedPrestationDTO[],
  products?: WrappedProductDTO[]
}
