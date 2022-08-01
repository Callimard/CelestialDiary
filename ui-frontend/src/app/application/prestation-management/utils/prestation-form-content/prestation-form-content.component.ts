import {Component, Input, OnInit} from '@angular/core';
import {PrestationFormGroup} from "../prestation-form-group";

@Component({
  selector: '[app-prestation-form-content]',
  templateUrl: './prestation-form-content.component.html',
  styleUrls: ['./prestation-form-content.component.css']
})
export class PrestationFormContentComponent implements OnInit {

  @Input() prestationFormGroup!: PrestationFormGroup;
  @Input() allDisabled: boolean = false;

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
    if (this.allDisabled) {
      this.prestationFormGroup.disable();
    }
  }

}
