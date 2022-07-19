import {FormControl, FormGroup} from "@angular/forms";
import {DatedTimeIntervalListDTO} from "../../data/general/time/dated-time-interval-list-dto";
import {TimeIntervalFormArray} from "./time-interval-form-array";
import {NonDatedTimeIntervalListDTO} from "../../data/general/time/non-dated-time-interval-list-dto";

export class DatedTimeIntervalFormGroup extends FormGroup {

  constructor(datedTimeIntervalList?: DatedTimeIntervalListDTO) {
    super({
      date: new FormControl(datedTimeIntervalList?.date),
      timeIntervals: new TimeIntervalFormArray(datedTimeIntervalList)
    });
  }

  public extractDatedTimeIntervalListDTO(): DatedTimeIntervalListDTO | undefined {
    const tIntervals: NonDatedTimeIntervalListDTO | undefined = (this.get('timeIntervals') as TimeIntervalFormArray).extractNonDatedTimeIntervalListDTO();

    if (this.get('date')?.value == null) {
      return undefined;
    }

    return {
      date: this.get('date')?.value,
      timeIntervals: tIntervals != null ? tIntervals.timeIntervals : []
    }
  }
}
