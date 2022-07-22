import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ElementListComponent} from "../element-list/element-list.component";

@Component({
  selector: '[app-searching-list]',
  templateUrl: './searching-list.component.html',
  styleUrls: ['./searching-list.component.css']
})
export class SearchingListComponent extends ElementListComponent implements OnInit {

  @Output() searchFilter = new EventEmitter<string>();

  constructor() {
    super();
    // Nothing
  }

  override ngOnInit(): void {
    super.ngOnInit();
    // Nothing
  }

  searchElement(filter: string) {
    this.searchFilter.emit(filter);
  }

}
