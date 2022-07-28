import {PrivilegeDTO} from "./privilege-dto";

export interface EstablishmentRoleDTO {
  establishmentId: string,
  establishmentPrivileges: PrivilegeDTO[]
}
