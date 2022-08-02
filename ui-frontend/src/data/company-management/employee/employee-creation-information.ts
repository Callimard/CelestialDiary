export interface EmployeeCreationInformation {
  email: string,
  password: string,
  firstName: string,
  lastName: string,
  comment?: string,
  gender?: string,
  phone?: string,
  praticablePrestations?: string[],
  tags: string[],
  roles: string[]
}
