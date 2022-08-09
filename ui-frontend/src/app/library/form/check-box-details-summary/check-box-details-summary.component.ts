import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";

@Component({
  selector: '[app-check-box-details-summary]',
  templateUrl: './check-box-details-summary.component.html',
  styleUrls: ['./check-box-details-summary.component.css']
})
export class CheckBoxDetailsSummaryComponent implements OnInit {

  @Input() checkBoxFormGroup!: FormGroup
  @Input() checkBoxControlName!: string
  @Input() summaryLabel?: string
  @Input() detailText?: string

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

}
