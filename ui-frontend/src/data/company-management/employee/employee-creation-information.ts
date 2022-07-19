export interface EmployeeCreationInformation {
  email: string,
  password: string,
  firstName: string,
  lastName: string,
  comment: string,
  gender: string,
  phone: string,
  isTechnician: boolean,
  tags: string[],
  roles: string[]
}
