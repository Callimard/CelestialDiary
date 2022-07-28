export interface JwtAccount {

  companyId: string;
  companyEmail: string;
  companyName: string;
  employeeEmail: string;
  employeeFirstName: string;
  employeeLastName: string;

  companyPrivilegeIdentifiers: string[];
  establishmentPrivilegeIdentifiers: { [id: string]: string[] };
}
