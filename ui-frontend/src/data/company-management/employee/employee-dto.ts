import {WrappedEstablishmentDTO} from "../establishment/wrapped-establishment-dto";

export interface EmployeeDTO {
  id: string,
  email: string,
  password: string,
  firstName: string,
  lastName: string,
  comment: string,
  photo: string,
  gender: string,
  phone: string,
  isTechnician: boolean,
  activated: boolean,
  tags: string[],
  assignedEstablishments: WrappedEstablishmentDTO[],
  creationDate: string
}
