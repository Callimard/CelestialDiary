import {Component, Input, OnInit} from '@angular/core';
import {BasicInputFormComponent} from "../basic-input-form/basic-input-form.component";

@Component({
  selector: '[app-advanced-basic-input-form]',
  templateUrl: './advanced-basic-input-form.component.html',
  styleUrls: ['./advanced-basic-input-form.component.css']
})
export class AdvancedBasicInputFormComponent extends BasicInputFormComponent implements OnInit {

  @Input() roundImage: boolean = true;
  @Input() imageSrc?: string;
  @Input() inputHeight: string = "60px"

  constructor() {
    super();
  }

  override ngOnInit(): void {
    super.ngOnInit();
  }

}
