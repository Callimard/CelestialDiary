import {WrappedEstablishmentDTO} from "../establishment/wrapped-establishment-dto";
import {RoleDTO} from "./role/role-dto";
import {PrestationDTO} from "../saleable/prestation/prestation-dto";

export interface EmployeeDTO {
  id: string,
  email: string,
  firstName: string,
  lastName: string,
  comment: string,
  photo: string,
  gender: string,
  phone: string,
  praticablePrestations: PrestationDTO[],
  activated: boolean,
  tags: string[],
  roles: RoleDTO[],
  assignedEstablishments: WrappedEstablishmentDTO[],
  creationDate: string
}
