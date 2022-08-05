import {Component, Input, OnInit} from '@angular/core';
import {DayHours} from "../../../../service/time/week-hours-form-group";

@Component({
  selector: '[app-day-hours-form]',
  templateUrl: './day-hours-form.component.html',
  styleUrls: ['./day-hours-form.component.css']
})
export class DayHoursFormComponent implements OnInit {

  @Input() day!: DayHours;
  @Input() allDisabled = false;

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

}
