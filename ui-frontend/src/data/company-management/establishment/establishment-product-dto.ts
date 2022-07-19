import {WrappedProductDTO} from "../saleable/product/wrapped-product-dto";
import {EstablishmentSaleableDTO} from "./establishment-saleable-dto";

export interface EstablishmentProductDTO extends EstablishmentSaleableDTO {
  remainingStock: number,
  product: WrappedProductDTO
}
