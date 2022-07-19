import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {EstablishmentManagementService} from "../../../../service/company-management/establishment-management.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {TimeIntervalFormGroup} from "../../../../service/time/time-interval-form-group";
import {TimeIntervalFormArray} from "../../../../service/time/time-interval-form-array";
import {EstablishmentCreationInformation} from "../../../../data/company-management/establishment/establishment-creation-information";


@Component({
  selector: '[app-establishment-creator]',
  templateUrl: './establishment-creator.component.html',
  styleUrls: ['./establishment-creator.component.css']
})
export class EstablishmentCreatorComponent implements OnInit {

  creationFailed = false;

  @Output() creationSuccess = new EventEmitter<boolean>();

  establishmentCreationForm = new FormGroup({
    name: new FormControl(null, [Validators.required]),
    description: new FormControl(),
    address: new FormGroup({
      street: new FormControl(null, [Validators.required]),
      zipCode: new FormControl(null, [Validators.required]),
      city: new FormControl(null, [Validators.required]),
      country: new FormControl(null, [Validators.required])
    }, [Validators.required]),
    openingHours: new FormGroup({
      mondayOpening: new TimeIntervalFormArray(),
      tuesdayOpening: new TimeIntervalFormArray(),
      wednesdayOpening: new TimeIntervalFormArray(),
      thursdayOpening: new TimeIntervalFormArray(),
      fridayOpening: new TimeIntervalFormArray(),
      saturdayOpening: new TimeIntervalFormArray(),
      sundayOpening: new TimeIntervalFormArray()
    })
  });

  constructor(private establishmentManagementService: EstablishmentManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  onEstablishmentCreation() {
    const form = this.establishmentCreationForm.value;
    const establishmentInfo: EstablishmentCreationInformation = {
      name: form.name,
      description: form.description,
      address: form.address,
      mondayOpening: this.mondayOpenings.extractNonDatedTimeIntervalListDTO(),
      tuesdayOpening: this.tuesdayOpenings.extractNonDatedTimeIntervalListDTO(),
      wednesdayOpening: this.wednesdayOpenings.extractNonDatedTimeIntervalListDTO(),
      thursdayOpening: this.thursdayOpenings.extractNonDatedTimeIntervalListDTO(),
      fridayOpening: this.fridayOpenings.extractNonDatedTimeIntervalListDTO(),
      saturdayOpening: this.saturdayOpenings.extractNonDatedTimeIntervalListDTO(),
      sundayOpening: this.sundayOpenings.extractNonDatedTimeIntervalListDTO()
    }
    console.log(establishmentInfo);
    this.establishmentManagementService.createEstablishment(establishmentInfo).then(() => {
      this.creationFailed = false;
      this.creationSuccess.emit(true);
    }).catch(() => {
      this.creationFailed = true;
      this.creationSuccess.emit(false);
    })
  }

  get openingHours(): FormGroup {
    return this.establishmentCreationForm.get('openingHours') as FormGroup
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

}
