import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: '[app-prestation-creation]',
  templateUrl: './prestation-creation.component.html',
  styleUrls: ['./prestation-creation.component.css']
})
export class PrestationCreationComponent implements OnInit {

  @Output() wantGoBack = new EventEmitter<boolean>();
  @Output() prestationHasBeenCreated = new EventEmitter<boolean>();

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  goBack() {
    this.wantGoBack.emit(true);
  }

}
