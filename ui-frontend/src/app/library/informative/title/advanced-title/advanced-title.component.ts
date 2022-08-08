import {Component, Input, OnInit} from '@angular/core';
import {BasicTitleComponent} from "../basic-title/basic-title.component";

@Component({
  selector: '[app-advanced-title]',
  templateUrl: './advanced-title.component.html',
  styleUrls: ['./advanced-title.component.css']
})
export class AdvancedTitleComponent extends BasicTitleComponent implements OnInit {

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
