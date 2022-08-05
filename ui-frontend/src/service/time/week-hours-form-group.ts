import {FormGroup} from "@angular/forms";
import {TimeIntervalFormArray} from "./time-interval-form-array";
import {TimeIntervalFormGroup} from "./time-interval-form-group";
import {NonDatedTimeIntervalListDTO} from "../../data/general/time/non-dated-time-interval-list-dto";

export interface DayHours {
  title: string,
  controlName: string,
  timeIntervalFormArray: TimeIntervalFormArray
}

export class WeekHoursFormGroup extends FormGroup {

  constructor(monday?: NonDatedTimeIntervalListDTO,
              tuesday?: NonDatedTimeIntervalListDTO,
              wednesday?: NonDatedTimeIntervalListDTO,
              thursday?: NonDatedTimeIntervalListDTO,
              friday?: NonDatedTimeIntervalListDTO,
              saturday?: NonDatedTimeIntervalListDTO,
              sunday?: NonDatedTimeIntervalListDTO) {
    super({
      monday: new TimeIntervalFormArray(monday),
      tuesday: new TimeIntervalFormArray(tuesday),
      wednesday: new TimeIntervalFormArray(wednesday),
      thursday: new TimeIntervalFormArray(thursday),
      friday: new TimeIntervalFormArray(friday),
      saturday: new TimeIntervalFormArray(saturday),
      sunday: new TimeIntervalFormArray(sunday)
    })
  }

  public get allDays(): DayHours[] {
    let days: DayHours[] = [];

    days.push(WeekHoursFormGroup.dayHoursOf('days.monday', 'monday', this.monday));
    days.push(WeekHoursFormGroup.dayHoursOf('days.tuesday', 'tuesday', this.tuesday));
    days.push(WeekHoursFormGroup.dayHoursOf('days.wednesday', 'wednesday', this.wednesday));
    days.push(WeekHoursFormGroup.dayHoursOf('days.thursday', 'thursday', this.thursday));
    days.push(WeekHoursFormGroup.dayHoursOf('days.friday', 'friday', this.friday));
    days.push(WeekHoursFormGroup.dayHoursOf('days.saturday', 'saturday', this.saturday));
    days.push(WeekHoursFormGroup.dayHoursOf('days.sunday', 'sunday', this.sunday));

    return days;
  }

  private static dayHoursOf(title: string, controlName: string, timeIntervalFormArray: TimeIntervalFormArray): DayHours {
    return {
      title: title,
      controlName: controlName,
      timeIntervalFormArray: timeIntervalFormArray
    }
  }

  public get monday(): TimeIntervalFormArray {
    return this.get('monday') as TimeIntervalFormArray;
  }

  public get mondayHours(): TimeIntervalFormGroup[] {
    return this.monday.timeIntervalControls;
  }

  public get tuesday(): TimeIntervalFormArray {
    return this.get('tuesday') as TimeIntervalFormArray;
  }

  public get tuesdayHours(): TimeIntervalFormGroup[] {
    return this.tuesday.timeIntervalControls;
  }

  public get wednesday(): TimeIntervalFormArray {
    return this.get('wednesday') as TimeIntervalFormArray;
  }

  public get wednesdayHours(): TimeIntervalFormGroup[] {
    return this.wednesday.timeIntervalControls;
  }

  public get thursday(): TimeIntervalFormArray {
    return this.get('thursday') as TimeIntervalFormArray;
  }

  public get thursdayHours(): TimeIntervalFormGroup[] {
    return this.thursday.timeIntervalControls;
  }

  public get friday(): TimeIntervalFormArray {
    return this.get('friday') as TimeIntervalFormArray;
  }

  public get fridayHours(): TimeIntervalFormGroup[] {
    return this.friday.timeIntervalControls;
  }

  public get saturday(): TimeIntervalFormArray {
    return this.get('saturday') as TimeIntervalFormArray;
  }

  public get saturdayHours(): TimeIntervalFormGroup[] {
    return this.saturday.timeIntervalControls;
  }

  public get sunday(): TimeIntervalFormArray {
    return this.get('sunday') as TimeIntervalFormArray;
  }

  public get sundayHours(): TimeIntervalFormGroup[] {
    return this.sunday.timeIntervalControls;
  }
}
