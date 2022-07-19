import {FormControl, FormGroup, Validators} from "@angular/forms";
import {TimeIntervalDTO} from "../../data/general/time/time-interval-dto";

export class TimeIntervalFormGroup extends FormGroup {
  constructor(timeInterval?: TimeIntervalDTO) {
    super({
      start: new FormControl(timeInterval?.start, [Validators.required]),
      end: new FormControl(timeInterval?.end, [Validators.required])
    });
  }

  public extractTimeIntervalDTO(): TimeIntervalDTO | null {
    if (this.get('start')?.value != null && this.get('end')?.value != null) {
      return {
        start: this.get('start')?.value,
        end: this.get('end')?.value
      };
    } else
      return null;
  }
}
