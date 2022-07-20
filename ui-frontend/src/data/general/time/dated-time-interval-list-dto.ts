import {NonDatedTimeIntervalListDTO} from "./non-dated-time-interval-list-dto";

export interface DatedTimeIntervalListDTO extends NonDatedTimeIntervalListDTO {
  date: string
}
