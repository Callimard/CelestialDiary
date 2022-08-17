import {NonDatedTimeIntervalListDTO} from "../../../../general/time/non-dated-time-interval-list-dto";

export interface WorkingHoursInformation {
  year: number,
  weekNumber: number,
  monday?: NonDatedTimeIntervalListDTO,
  tuesday?: NonDatedTimeIntervalListDTO,
  wednesday?: NonDatedTimeIntervalListDTO,
  thursday?: NonDatedTimeIntervalListDTO,
  friday?: NonDatedTimeIntervalListDTO,
  saturday?: NonDatedTimeIntervalListDTO,
  sunday?: NonDatedTimeIntervalListDTO
}
