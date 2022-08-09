export interface RoleCreationInformation {
  name: string,
  description?: string,
  companyPrivilegeIdentifiers?: string[],
  establishmentRoles?: {
    [establishmentId: string]: string[]
  }
}
