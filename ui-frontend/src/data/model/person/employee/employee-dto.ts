import {WrappedEstablishmentDTO} from "../../establishment/wrapped-establishment-dto";
import {RoleDTO} from "./role/role-dto";
import {PrestationDTO} from "../../saleable/prestation/prestation-dto";
import {PersonDTO} from "../person-dto";

export interface EmployeeDTO extends PersonDTO {
  praticablePrestations: PrestationDTO[],
  activated: boolean,
  tags: string[],
  roles: RoleDTO[],
  assignedEstablishments: WrappedEstablishmentDTO[]
}
