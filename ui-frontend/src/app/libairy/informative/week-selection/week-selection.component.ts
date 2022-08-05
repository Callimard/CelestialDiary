import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {DateService} from "../../../../service/time/date.service";

@Component({
  selector: '[app-week-selection]',
  templateUrl: './week-selection.component.html',
  styleUrls: ['./week-selection.component.css']
})
export class WeekSelectionComponent implements OnInit, OnChanges {

  @Input() dateOfWeek!: Date;

  firstDayOfWeek: Date = new Date();
  lastDayOfWeek: Date = new Date();

  @Output() previousWeek = new EventEmitter<Date>();
  @Output() nextWeek = new EventEmitter<Date>();

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing.
  }

  ngOnChanges(_changes: SimpleChanges): void {
    this.updateDate();
  }

  private updateDate() {
    this.firstDayOfWeek = DateService.firstDayOfWeek(this.dateOfWeek);
    this.lastDayOfWeek = DateService.lastDayOfWeek(this.dateOfWeek);
  }

  dateToString(date: Date): string {
    return DateService.toYearWeekDay(date);
  }

  passToPreviousWeek() {
    let tmp = new Date(this.firstDayOfWeek);
    tmp.setDate(this.firstDayOfWeek.getDate() - 7);
    this.dateOfWeek = tmp;

    this.updateDate();

    this.previousWeek.emit(this.dateOfWeek);
  }

  passToNextWeek() {
    let tmp = new Date(this.lastDayOfWeek);
    tmp.setDate(this.lastDayOfWeek.getDate() + 1);
    this.dateOfWeek = tmp;

    this.updateDate();

    this.nextWeek.emit(this.dateOfWeek);
  }

}
