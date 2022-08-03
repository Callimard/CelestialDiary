import {FormControl, FormGroup, Validators} from "@angular/forms";
import {TimeIntervalFormArray} from "../../../../service/time/time-interval-form-array";
import {TimeIntervalFormGroup} from "../../../../service/time/time-interval-form-group";
import {EstablishmentDTO} from "../../../../data/company-management/establishment/establishment-dto";
import {ArrayDatedTimeIntervalFormArray} from "../../../../service/time/array-dated-time-interval-form-array";
import {DatedTimeIntervalFormGroup} from "../../../../service/time/dated-time-interval-form-group";

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
      openingHours: new FormGroup({
        mondayOpening: new TimeIntervalFormArray(establishment?.mondayOpening),
        tuesdayOpening: new TimeIntervalFormArray(establishment?.tuesdayOpening),
        wednesdayOpening: new TimeIntervalFormArray(establishment?.wednesdayOpening),
        thursdayOpening: new TimeIntervalFormArray(establishment?.thursdayOpening),
        fridayOpening: new TimeIntervalFormArray(establishment?.fridayOpening),
        saturdayOpening: new TimeIntervalFormArray(establishment?.saturdayOpening),
        sundayOpening: new TimeIntervalFormArray(establishment?.sundayOpening)
      }),
      exceptionalOpening: new ArrayDatedTimeIntervalFormArray(establishment?.exceptionalOpening),
      exceptionalClosing: new ArrayDatedTimeIntervalFormArray(establishment?.exceptionalClosing)
    });
  }

  get openingHours(): FormGroup {
    return this.get('openingHours') as FormGroup
  }

  get mondayOpenings(): TimeIntervalFormArray {
    return this.openingHours.get('mondayOpening') as TimeIntervalFormArray;
  }

  get mondayOpeningsControls(): TimeIntervalFormGroup[] {
    return this.mondayOpenings.controls as TimeIntervalFormGroup[];
  }

  addMondayHours() {
    this.mondayOpenings.push(new TimeIntervalFormGroup());
  }

  removeMondayHours(index: number) {
    this.mondayOpenings.removeAt(index);
  }

  get tuesdayOpenings(): TimeIntervalFormArray {
    return this.openingHours.get('tuesdayOpening') as TimeIntervalFormArray;
  }

  get tuesdayOpeningsControls(): TimeIntervalFormGroup[] {
    return this.tuesdayOpenings.controls as TimeIntervalFormGroup[];
  }

  addTuesdayHours() {
    this.tuesdayOpenings.push(new TimeIntervalFormGroup());
  }

  removeTuesdayHours(index: number) {
    this.tuesdayOpenings.removeAt(index);
  }

  get wednesdayOpenings(): TimeIntervalFormArray {
    return this.openingHours.get('wednesdayOpening') as TimeIntervalFormArray;
  }

  get wednesdayOpeningsControls(): TimeIntervalFormGroup[] {
    return this.wednesdayOpenings.controls as TimeIntervalFormGroup[];
  }

  addWednesdayHours() {
    this.wednesdayOpenings.push(new TimeIntervalFormGroup());
  }

  removeWednesdayHours(index: number) {
    this.wednesdayOpenings.removeAt(index);
  }

  get thursdayOpenings(): TimeIntervalFormArray {
    return this.openingHours.get('thursdayOpening') as TimeIntervalFormArray;
  }


  get thursdayOpeningsControls(): TimeIntervalFormGroup[] {
    return this.thursdayOpenings.controls as TimeIntervalFormGroup[];
  }

  addThursdayHours() {
    this.thursdayOpenings.push(new TimeIntervalFormGroup());
  }

  removeThursdayHours(index: number) {
    this.thursdayOpenings.removeAt(index);
  }

  get fridayOpenings(): TimeIntervalFormArray {
    return this.openingHours.get('fridayOpening') as TimeIntervalFormArray;
  }

  get fridayOpeningsControls(): TimeIntervalFormGroup[] {
    return this.fridayOpenings.controls as TimeIntervalFormGroup[];
  }

  addFridayHours() {
    this.fridayOpenings.push(new TimeIntervalFormGroup());
  }

  removeFridayHours(index: number) {
    this.fridayOpenings.removeAt(index);
  }

  get saturdayOpenings(): TimeIntervalFormArray {
    return this.openingHours.get('saturdayOpening') as TimeIntervalFormArray;
  }

  get saturdayOpeningsControls(): TimeIntervalFormGroup[] {
    return this.saturdayOpenings.controls as TimeIntervalFormGroup[];
  }

  addSaturdayHours() {
    this.saturdayOpenings.push(new TimeIntervalFormGroup());
  }

  removeSaturdayHours(index: number) {
    this.saturdayOpenings.removeAt(index);
  }

  get sundayOpenings(): TimeIntervalFormArray {
    return this.openingHours.get('sundayOpening') as TimeIntervalFormArray;
  }

  get sundayOpeningsControls(): TimeIntervalFormGroup[] {
    return this.sundayOpenings.controls as TimeIntervalFormGroup[];
  }

  addSundayHours() {
    this.sundayOpenings.push(new TimeIntervalFormGroup());
  }

  removeSundayHours(index: number) {
    this.sundayOpenings.removeAt(index);
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
