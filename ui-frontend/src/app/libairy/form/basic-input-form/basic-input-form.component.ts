import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormGroup} from "@angular/forms";

@Component({
  selector: '[app-basic-input-form]',
  templateUrl: './basic-input-form.component.html',
  styleUrls: ['./basic-input-form.component.css']
})
export class BasicInputFormComponent implements OnInit {

  @Input() group!: FormGroup;
  @Input() controlName!: string;

  @Input() labelFor?: string;
  @Input() label?: string
  @Input() inputType: string = "text";
  @Input() placeHolder?: string;

  @Output() inputChange = new EventEmitter<string>();

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

}
