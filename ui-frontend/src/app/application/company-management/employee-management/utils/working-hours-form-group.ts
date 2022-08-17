import {WrappedEmployeeWorkingHoursDTO} from "../../../../../data/model/person/employee/working-hours/wrapped-employee-working-hours-dto";
import {WeekHoursFormGroup} from "../../../../../service/time/week-hours-form-group";
import {WorkingHoursInformation} from "../../../../../data/model/person/employee/working-hours/working-hours-information";

export class WorkingHoursFormGroup extends WeekHoursFormGroup {

  constructor(workingHours?: WrappedEmployeeWorkingHoursDTO) {
    super(workingHours?.monday,
      workingHours?.tuesday,
      workingHours?.wednesday,
      workingHours?.thursday,
      workingHours?.friday,
      workingHours?.saturday,
      workingHours?.sunday);
  }

  public toWorkingHoursInformation(year: number, weekNumber: number): WorkingHoursInformation {
    return {
      year: year,
      weekNumber: weekNumber,
      monday: this.monday.extractNonDatedTimeIntervalListDTO(),
      tuesday: this.tuesday.extractNonDatedTimeIntervalListDTO(),
      wednesday: this.wednesday.extractNonDatedTimeIntervalListDTO(),
      thursday: this.thursday.extractNonDatedTimeIntervalListDTO(),
      friday: this.friday.extractNonDatedTimeIntervalListDTO(),
      saturday: this.saturday.extractNonDatedTimeIntervalListDTO(),
      sunday: this.sunday.extractNonDatedTimeIntervalListDTO()
    }
  }
}
