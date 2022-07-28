import {FormArray} from "@angular/forms";
import {NonDatedTimeIntervalListDTO} from "../../data/general/time/non-dated-time-interval-list-dto";
import {TimeIntervalDTO} from "../../data/general/time/time-interval-dto";
import {TimeIntervalFormGroup} from "./time-interval-form-group";

export class TimeIntervalFormArray extends FormArray {
  constructor(nonDatedTimeIntervalListDTO?: NonDatedTimeIntervalListDTO, disabled: boolean = false) {
    super([]);

    if (nonDatedTimeIntervalListDTO != null) {
      for (let tI of nonDatedTimeIntervalListDTO.timeIntervals) {
        if (tI != null) {
          this.push(new TimeIntervalFormGroup(tI, disabled));
        }
      }
    }
  }

  public extractNonDatedTimeIntervalListDTO(): NonDatedTimeIntervalListDTO | undefined {
    let tIntervals: TimeIntervalDTO[] = [];
    const tIntervalForms: TimeIntervalFormGroup[] = this.controls as TimeIntervalFormGroup[];
    for (let tIntervalFormGroup of tIntervalForms) {
      let tInterval: TimeIntervalDTO | null = tIntervalFormGroup.extractTimeIntervalDTO();
      if (tInterval != null) {
        tIntervals.push(tInterval);
      }
    }

    if (tIntervals.length <= 0) {
      return undefined;
    }

    return {
      timeIntervals: tIntervals
    };
  }
}
