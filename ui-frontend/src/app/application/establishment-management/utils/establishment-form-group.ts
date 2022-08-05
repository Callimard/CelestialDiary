import {FormControl, FormGroup, Validators} from "@angular/forms";
import {TimeIntervalFormArray} from "../../../../service/time/time-interval-form-array";
import {TimeIntervalFormGroup} from "../../../../service/time/time-interval-form-group";
import {EstablishmentDTO} from "../../../../data/company-management/establishment/establishment-dto";
import {ArrayDatedTimeIntervalFormArray} from "../../../../service/time/array-dated-time-interval-form-array";
import {DatedTimeIntervalFormGroup} from "../../../../service/time/dated-time-interval-form-group";
import {WeekHoursFormGroup} from "../../../../service/time/week-hours-form-group";
import {EstablishmentUpdatedInformation} from "../../../../data/company-management/establishment/establishment-updated-information";
import {DatedTimeIntervalListDTO} from "../../../../data/general/time/dated-time-interval-list-dto";
import {EstablishmentCreationInformation} from "../../../../data/company-management/establishment/establishment-creation-information";

export class EstablishmentFormGroup extends FormGroup {
  constructor(withValidators = false, establishment?: EstablishmentDTO) {
    super({
      name: new FormControl(establishment?.name, withValidators ? [Validators.required] : []),
      description: new FormControl(establishment?.description),
      address: new FormGroup({
        street: new FormControl(establishment?.address.street, withValidators ? [Validators.required] : []),
        zipCode: new FormControl(establishment?.address.zipCode, withValidators ? [Validators.required] : []),
        city: new FormControl(establishment?.address.city, withValidators ? [Validators.required] : []),
        country: new FormControl(establishment?.address.country, withValidators ? [Validators.required] : [])
      }, withValidators ? [Validators.required] : []),
      openingHours: new WeekHoursFormGroup(
        establishment?.mondayOpening,
        establishment?.tuesdayOpening,
        establishment?.wednesdayOpening,
        establishment?.thursdayOpening,
        establishment?.fridayOpening,
        establishment?.saturdayOpening,
        establishment?.sundayOpening),
      exceptionalOpening: new ArrayDatedTimeIntervalFormArray(establishment?.exceptionalOpening),
      exceptionalClosing: new ArrayDatedTimeIntervalFormArray(establishment?.exceptionalClosing)
    });
  }


  public extractEstablishmentCreationInformation(): EstablishmentCreationInformation {
    const v = this.value;

    return {
      name: v.name,
      description: v.description,
      address: v.address,
      mondayOpening: this.openingHours.monday.extractNonDatedTimeIntervalListDTO(),
      tuesdayOpening: this.openingHours.tuesday.extractNonDatedTimeIntervalListDTO(),
      wednesdayOpening: this.openingHours.wednesday.extractNonDatedTimeIntervalListDTO(),
      thursdayOpening: this.openingHours.thursday.extractNonDatedTimeIntervalListDTO(),
      fridayOpening: this.openingHours.friday.extractNonDatedTimeIntervalListDTO(),
      saturdayOpening: this.openingHours.saturday.extractNonDatedTimeIntervalListDTO(),
      sundayOpening: this.openingHours.sunday.extractNonDatedTimeIntervalListDTO()
    }
  }

  public extractEstablishmentUpdatedInformation(): EstablishmentUpdatedInformation {
    const v = this.value;

    let exceptionOpening: DatedTimeIntervalListDTO[] | null = this.exceptionalOpenings.extractToDatedTimeIntervalListDTOArray();
    let exceptionClosing: DatedTimeIntervalListDTO[] | null = this.exceptionalClosings.extractToDatedTimeIntervalListDTOArray();

    return {
      name: v.name,
      description: v.description,
      address: v.address,
      mondayOpening: this.openingHours.monday.extractNonDatedTimeIntervalListDTO(),
      tuesdayOpening: this.openingHours.tuesday.extractNonDatedTimeIntervalListDTO(),
      wednesdayOpening: this.openingHours.wednesday.extractNonDatedTimeIntervalListDTO(),
      thursdayOpening: this.openingHours.thursday.extractNonDatedTimeIntervalListDTO(),
      fridayOpening: this.openingHours.friday.extractNonDatedTimeIntervalListDTO(),
      saturdayOpening: this.openingHours.saturday.extractNonDatedTimeIntervalListDTO(),
      sundayOpening: this.openingHours.sunday.extractNonDatedTimeIntervalListDTO(),
      exceptionalOpening: exceptionOpening != null ? exceptionOpening : undefined,
      exceptionalClosing: exceptionClosing != null ? exceptionClosing : undefined
    }
  }

  get openingHours(): WeekHoursFormGroup {
    return this.get('openingHours') as WeekHoursFormGroup
  }

  get exceptionalOpenings(): ArrayDatedTimeIntervalFormArray {
    return (this.get('exceptionalOpening') as ArrayDatedTimeIntervalFormArray);
  }

  get exceptionOpeningsControls(): DatedTimeIntervalFormGroup[] {
    return this.exceptionalOpenings.controls as DatedTimeIntervalFormGroup[];
  }

  addExceptionalOpening() {
    this.exceptionalOpenings.push(new DatedTimeIntervalFormGroup());
  }

  removeExceptionalOpening(index: number) {
    this.exceptionalOpenings.removeAt(index);
  }

  get exceptionalClosings(): ArrayDatedTimeIntervalFormArray {
    return (this.get('exceptionalClosing') as ArrayDatedTimeIntervalFormArray);
  }

  get exceptionClosingsControls(): DatedTimeIntervalFormGroup[] {
    return this.exceptionalClosings.controls as DatedTimeIntervalFormGroup[];
  }

  addExceptionalClosing() {
    this.exceptionalClosings.push(new DatedTimeIntervalFormGroup());
  }

  removeExceptionalClosing(index: number) {
    this.exceptionalClosings.removeAt(index);
  }

  getTimeIntervalFormControls(formGroup: DatedTimeIntervalFormGroup): TimeIntervalFormGroup[] {
    return (formGroup.get('timeIntervals') as TimeIntervalFormArray).controls as TimeIntervalFormGroup[];
  }

  addTimeIntervalFormGroup(formGroup: DatedTimeIntervalFormGroup) {
    (formGroup.get('timeIntervals') as TimeIntervalFormArray).push(new TimeIntervalFormGroup());
  }

  removeTimeIntervalForm(formGroup: DatedTimeIntervalFormGroup, index: number) {
    const timeIntervalFormArray = (formGroup.get('timeIntervals') as TimeIntervalFormArray);
    timeIntervalFormArray.removeAt(index);
  }
}
