import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: '[app-title-card]',
  templateUrl: './title-card.component.html',
  styleUrls: ['./title-card.component.css']
})
export class TitleCardComponent implements OnInit {

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
