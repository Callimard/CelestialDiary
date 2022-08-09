import {Component, ContentChild, EventEmitter, Input, OnInit, Output, TemplateRef} from '@angular/core';
import {PaneInfo, PaneInfoTransformer} from "../../informative/info-pane/info-pane.component";

@Component({
  selector: '[app-element-list]',
  templateUrl: './element-list.component.html',
  styleUrls: ['./element-list.component.css']
})
export class ElementListComponent implements OnInit {

  @Input() allElements: any[] | PaneInfo[] = [];
  @Input() listPaddingHeight = "0px";
  @Input() itemHeight: string = "80px";
  @Input() paneInfoTransformer?: PaneInfoTransformer<any>;

  @ContentChild('item') itemTemplate: TemplateRef<any> | undefined;

  @Output() elementSelected = new EventEmitter<any>();

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }
}
