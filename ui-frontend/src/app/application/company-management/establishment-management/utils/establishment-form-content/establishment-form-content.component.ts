import {Component, Input, OnInit} from '@angular/core';
import {EstablishmentFormGroup} from "../establishment-form-group";

@Component({
  selector: '[app-establishment-form-content]',
  templateUrl: './establishment-form-content.component.html',
  styleUrls: ['./establishment-form-content.component.css']
})
export class EstablishmentFormContentComponent implements OnInit {

  @Input() establishmentFormGroup!: EstablishmentFormGroup;
  @Input() displayExceptionalClose: boolean = false;
  @Input() allDisabled: boolean = false;

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

}
