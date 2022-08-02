import {PrivilegeDTO} from "../../../security/privilege/privilege-dto";

export interface EstablishmentRoleDTO {
  establishmentId: string,
  establishmentPrivileges: PrivilegeDTO[]
}
