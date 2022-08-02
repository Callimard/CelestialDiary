import {EstablishmentRoleDTO} from "./establishment-role-dto";

export interface RoleCreationInformation {
  name: string,
  description?: string,
  companyPrivilegeIdentifiers?: string[],
  establishmentRoles?: EstablishmentRoleDTO[]
}
