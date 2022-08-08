import {Component, ContentChild, EventEmitter, Input, OnInit, Output, TemplateRef} from '@angular/core';
import {ElementListComponent} from "../element-list/element-list.component";

@Component({
  selector: '[app-searching-list]',
  templateUrl: './searching-list.component.html',
  styleUrls: ['./searching-list.component.css']
})
export class SearchingListComponent extends ElementListComponent implements OnInit {

  @Input() searchBarPlaceHolder: string = "";

  @ContentChild("searchBar") searchBarTemplate: TemplateRef<any> | undefined;

  @Output() searchFilter = new EventEmitter<string>();

  constructor() {
    super();
  }

  override ngOnInit(): void {
    super.ngOnInit();
  }

  searchElement(filter: string) {
    this.searchFilter.emit(filter);
  }

}
