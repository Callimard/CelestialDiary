import {FormArray} from "@angular/forms";
import {DatedTimeIntervalListDTO} from "../../data/general/time/dated-time-interval-list-dto";
import {DatedTimeIntervalFormGroup} from "./dated-time-interval-form-group";

export class ArrayDatedTimeIntervalFormArray extends FormArray {

  constructor(listDatedTimeIntervalList?: DatedTimeIntervalListDTO[]) {
    super([]);

    if (listDatedTimeIntervalList != null && listDatedTimeIntervalList.length > 0) {
      for (let dateTimeIntervalList of listDatedTimeIntervalList) {
        if (dateTimeIntervalList != null) {
          this.push(new DatedTimeIntervalFormGroup(dateTimeIntervalList));
        }
      }
    }
  }

  public extractToDatedTimeIntervalListDTOArray(): DatedTimeIntervalListDTO[] {

    if (this.controls != null && this.controls.length > 0) {
      let array: DatedTimeIntervalListDTO[] = [];
      const datedTimeIntervalFormGroups = (this.controls as DatedTimeIntervalFormGroup[]);
      for (let datedTimeIntervalFormGroup of datedTimeIntervalFormGroups) {
        const dto = datedTimeIntervalFormGroup.extractDatedTimeIntervalListDTO();
        if (dto != null)
          array.push(dto)
      }
      return array
    } else {
      return [];
    }
  }
}
