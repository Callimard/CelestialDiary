import {Address} from "../../general/address";

export interface WrappedEstablishmentDTO {
  id: string,
  name: string,
  description?: string,
  address: Address,
  activated: boolean
}
