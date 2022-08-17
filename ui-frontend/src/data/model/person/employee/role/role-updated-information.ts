export interface RoleUpdatedInformation {
  name?: string,
  description?: string,
  companyPrivilegeIdentifiers?: string[],
  establishmentRoles?: {
    [establishmentId: string]: string[]
  }
}
