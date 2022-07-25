import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: '[app-basic-title]',
  templateUrl: './basic-title.component.html',
  styleUrls: ['./basic-title.component.css']
})
export class BasicTitleComponent implements OnInit {

  @Input() title!: string
  @Input() displayBackArrow: boolean = true;
  @Output() goBack = new EventEmitter<boolean>();

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

}
