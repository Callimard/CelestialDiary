import {PersonDTO} from "../person-dto";

export interface ClientDTO extends PersonDTO {
  origin: string,
  technicalComment: string
}
