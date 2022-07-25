import {Component, Input, OnInit} from '@angular/core';
import {TitleCardComponent} from "../title-card/title-card.component";

@Component({
  selector: '[app-card-advanced-title]',
  templateUrl: './advanced-title-card.component.html',
  styleUrls: ['./advanced-title-card.component.css']
})
export class AdvancedTitleCardComponent extends TitleCardComponent implements OnInit {

  @Input() subtitle?: string
  @Input() img?: string

  constructor() {
    super();
  }

  override ngOnInit(): void {
    super.ngOnInit()
    // Nothing
  }

}
