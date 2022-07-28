import {PrivilegeDTO} from "./privilege-dto";
import {EstablishmentRoleDTO} from "./establishment-role-dto";

export interface RoleDTO {
  id: string,
  name: string,
  description: string,
  companyPrivileges: PrivilegeDTO[],
  establishmentRoles: EstablishmentRoleDTO
}
