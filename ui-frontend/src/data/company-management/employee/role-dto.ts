import {PrivilegeDTO} from "./privilege-dto";
import {WrappedEstablishmentDTO} from "../establishment/wrapped-establishment-dto";

export interface RoleDTO {
  id: string,
  name: string,
  description: string,
  privileges: PrivilegeDTO[],
  establishments: WrappedEstablishmentDTO[]
}
