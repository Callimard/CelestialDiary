import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-selector',
  templateUrl: './selector.component.html',
  styleUrls: ['./selector.component.css']
})
export class SelectorComponent implements OnInit {

  @Output() selectedElement = new EventEmitter<any>();

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

}
