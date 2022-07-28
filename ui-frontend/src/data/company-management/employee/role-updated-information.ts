import {EstablishmentRoleDTO} from "./establishment-role-dto";

export interface RoleUpdatedInformation {
  name?: string,
  description?: string,
  companyPrivilegeIdentifiers?: string[],
  establishmentRoles?: EstablishmentRoleDTO[]
}
