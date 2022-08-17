import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: '[app-bundle-creation]',
  templateUrl: './bundle-creation.component.html',
  styleUrls: ['./bundle-creation.component.css']
})
export class BundleCreationComponent implements OnInit {

  @Output() wantGoBack = new EventEmitter<boolean>();
  @Output() bundleHasBeenCreated = new EventEmitter<boolean>();

  constructor() {
    // Nothing.
  }

  ngOnInit(): void {
    // Nothing
  }

  goBack() {
    this.wantGoBack.emit(true);
  }
}
