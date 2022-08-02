import {WrappedEstablishmentDTO} from "../establishment/wrapped-establishment-dto";
import {RoleDTO} from "./role/role-dto";

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
  roles: RoleDTO[],
  assignedEstablishments: WrappedEstablishmentDTO[],
  creationDate: string
}
