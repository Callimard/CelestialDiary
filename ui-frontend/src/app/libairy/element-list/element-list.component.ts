import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: '[app-element-list]',
  templateUrl: './element-list.component.html',
  styleUrls: ['./element-list.component.css']
})
export class ElementListComponent implements OnInit {

  @Input() allElements: any[] = [];
  @Input() listPaddingHeight = "0px";
  @Input() elementSize: string = "75px";

  @Input() titlePrefix: string = ""
  @Input() elementTitleField!: string
  @Input() titleSuffix: string = ""

  @Input() subTitlePrefix: string = ""
  @Input() elementSubtitleField?: string
  @Input() subTitleSuffix: string = ""

  @Input() elementImageField?: string
  @Input() roundImage: boolean = true

  @Output() elementSelected = new EventEmitter<any>();

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  onTicketClick(element: any) {
    this.elementSelected.emit(element);
  }

}
