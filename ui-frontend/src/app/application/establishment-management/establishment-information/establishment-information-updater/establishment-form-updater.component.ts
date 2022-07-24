import {Component, Input, OnInit} from '@angular/core';
import {EstablishmentDTO} from "../../../../../data/company-management/establishment/establishment-dto";
import {FormControl, FormGroup} from "@angular/forms";
import {EstablishmentManagementService} from "../../../../../service/company-management/establishment-management.service";
import {TimeIntervalFormArray} from "../../../../../service/time/time-interval-form-array";
import {TimeIntervalFormGroup} from "../../../../../service/time/time-interval-form-group";
import {EstablishmentUpdatedInformation} from "../../../../../data/company-management/establishment/establishment-updated-information";
import {ArrayDatedTimeIntervalFormArray} from "../../../../../service/time/array-dated-time-interval-form-array";
import {DatedTimeIntervalFormGroup} from "../../../../../service/time/dated-time-interval-form-group";
import {DatedTimeIntervalListDTO} from "../../../../../data/general/time/dated-time-interval-list-dto";

@Component({
  selector: '[app-establishment-form-updater]',
  templateUrl: './establishment-form-updater.component.html',
  styleUrls: ['./establishment-form-updater.component.css']
})
export class EstablishmentFormUpdaterComponent implements OnInit {

  @Input() establishment!: EstablishmentDTO;

  updateFailed = false;

  establishmentUpdateForm!: FormGroup;

  constructor(private establishmentManagerService: EstablishmentManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    this.initializeEstablishmentFormGroup();
  }

  private initializeEstablishmentFormGroup() {
    this.establishmentUpdateForm = new FormGroup({
      name: new FormControl(this.establishment.name),
      description: new FormControl(this.establishment.description),
      address: new FormGroup({
        street: new FormControl(this.establishment.address.street),
        zipCode: new FormControl(this.establishment.address.zipCode),
        city: new FormControl(this.establishment.address.city),
        country: new FormControl(this.establishment.address.country)
      }),
      openingHours: new FormGroup({
        mondayOpening: new TimeIntervalFormArray(this.establishment.mondayOpening),
        tuesdayOpening: new TimeIntervalFormArray(this.establishment.tuesdayOpening),
        wednesdayOpening: new TimeIntervalFormArray(this.establishment.wednesdayOpening),
        thursdayOpening: new TimeIntervalFormArray(this.establishment.thursdayOpening),
        fridayOpening: new TimeIntervalFormArray(this.establishment.fridayOpening),
        saturdayOpening: new TimeIntervalFormArray(this.establishment.saturdayOpening),
        sundayOpening: new TimeIntervalFormArray(this.establishment.sundayOpening)
      }),
      exceptionalOpening: new ArrayDatedTimeIntervalFormArray(this.establishment.exceptionalOpening),
      exceptionalClosing: new ArrayDatedTimeIntervalFormArray(this.establishment.exceptionalClosing)
    });
  }

  onUpdate() {
    const form = this.establishmentUpdateForm?.value;

    let exceptionOpening: DatedTimeIntervalListDTO[] | null = this.exceptionalOpenings.extractToDatedTimeIntervalListDTOArray();
    let exceptionClosing: DatedTimeIntervalListDTO[] | null = this.exceptionalClosings.extractToDatedTimeIntervalListDTOArray();

    const establishmentUpdates: EstablishmentUpdatedInformation = {
      name: form.name,
      description: form.description,
      address: form.address,
      mondayOpening: this.mondayOpenings.extractNonDatedTimeIntervalListDTO(),
      tuesdayOpening: this.tuesdayOpenings.extractNonDatedTimeIntervalListDTO(),
      wednesdayOpening: this.wednesdayOpenings.extractNonDatedTimeIntervalListDTO(),
      thursdayOpening: this.thursdayOpenings.extractNonDatedTimeIntervalListDTO(),
      fridayOpening: this.fridayOpenings.extractNonDatedTimeIntervalListDTO(),
      saturdayOpening: this.saturdayOpenings.extractNonDatedTimeIntervalListDTO(),
      sundayOpening: this.sundayOpenings.extractNonDatedTimeIntervalListDTO(),
      exceptionalOpening: exceptionOpening != null ? exceptionOpening : undefined,
      exceptionalClosing: exceptionClosing != null ? exceptionClosing : undefined
    }

    this.establishmentManagerService.updateEstablishment(this.establishment.id, establishmentUpdates).then((wrappedEstablishment) => {
      this.establishment.name = wrappedEstablishment.name;
      this.establishment.description = wrappedEstablishment.description;
      this.establishment.address = wrappedEstablishment.address;
      this.establishment.activated = wrappedEstablishment.activated;
      this.updateFailed = false;
    }).catch(() => {
      this.updateFailed = true;
    });
  }

  get openingHours(): FormGroup {
    return this.establishmentUpdateForm?.get('openingHours') as FormGroup
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
    return (this.establishmentUpdateForm.get('exceptionalOpening') as ArrayDatedTimeIntervalFormArray);
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
    return (this.establishmentUpdateForm.get('exceptionalClosing') as ArrayDatedTimeIntervalFormArray);
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
