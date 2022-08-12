import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";

@Component({
  selector: '[app-equipment-form-content]',
  templateUrl: './equipment-form-content.component.html',
  styleUrls: ['./equipment-form-content.component.css']
})
export class EquipmentFormContentComponent implements OnInit {

  @Input() equipmentFormGroup!: FormGroup
  @Input() allDisabled = false;

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    if (this.allDisabled) {
      this.equipmentFormGroup.disable();
    }
  }

}
