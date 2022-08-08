import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: '[app-search-bar]',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css']
})
export class SearchBarComponent implements OnInit {

  @Input() placeHolder: string = "";
  @Output() inputText = new EventEmitter<string>();

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  inputTextOccurs(event: Event) {
    this.inputText.emit((event.target as HTMLInputElement).value);
  }

}
