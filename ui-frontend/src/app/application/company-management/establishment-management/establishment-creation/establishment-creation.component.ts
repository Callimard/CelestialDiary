import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: '[app-establishment-creation]',
  templateUrl: './establishment-creation.component.html',
  styleUrls: ['./establishment-creation.component.css']
})
export class EstablishmentCreationComponent implements OnInit {

  @Output() wantGoBack = new EventEmitter<boolean>();
  @Output() establishmentHasBeenCreated = new EventEmitter<boolean>();

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
