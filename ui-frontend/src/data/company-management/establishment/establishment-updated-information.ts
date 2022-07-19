import {DatedTimeIntervalListDTO} from "../../general/time/dated-time-interval-list-dto";
import {Address} from "../../general/address";
import {NonDatedTimeIntervalListDTO} from "../../general/time/non-dated-time-interval-list-dto";

export interface EstablishmentUpdatedInformation {
  name?: string,
  description?: string,
  address?: Address,
  mondayOpening?: NonDatedTimeIntervalListDTO,
  tuesdayOpening?: NonDatedTimeIntervalListDTO,
  wednesdayOpening?: NonDatedTimeIntervalListDTO,
  thursdayOpening?: NonDatedTimeIntervalListDTO,
  fridayOpening?: NonDatedTimeIntervalListDTO,
  saturdayOpening?: NonDatedTimeIntervalListDTO,
  sundayOpening?: NonDatedTimeIntervalListDTO,
  exceptionalOpening?: DatedTimeIntervalListDTO[],
  exceptionalClosing?: DatedTimeIntervalListDTO[]
}
