import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: '[app-floating-card-button]',
  templateUrl: './floating-card-button.component.html',
  styleUrls: ['./floating-card-button.component.css']
})
export class FloatingCardButtonComponent implements OnInit {

  @Input() text?: string;
  @Input() textColor: string = "red";
  @Input() textFontWeight: string = "bold";
  @Input() textSize: string = "15px";

  @Output() buttonClick = new EventEmitter<boolean>();

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

}
