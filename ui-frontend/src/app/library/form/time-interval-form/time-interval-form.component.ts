import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {TimeIntervalFormGroup} from "../../../../service/time/time-interval-form-group";

@Component({
  selector: '[app-time-interval-form]',
  templateUrl: './time-interval-form.component.html',
  styleUrls: ['./time-interval-form.component.css']
})
export class TimeIntervalFormComponent implements OnInit {

  @Input() prefix!: string;
  @Input() timeIntervalFormGroup!: TimeIntervalFormGroup;
  @Input() index!: number
  @Input() disableRemoveButton = false;
  @Output() remove = new EventEmitter<number>();

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  removeTimeIntervalForm() {
    this.remove.emit(this.index);
  }

}
