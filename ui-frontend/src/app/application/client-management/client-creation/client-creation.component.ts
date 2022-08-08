import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: '[app-client-creation]',
  templateUrl: './client-creation.component.html',
  styleUrls: ['./client-creation.component.css']
})
export class ClientCreationComponent implements OnInit {


  @Output() clientHasBeenCreated = new EventEmitter<boolean>();
  @Output() wantGoBack = new EventEmitter<boolean>();

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  goBack(success: boolean) {
    if (success)
      this.wantGoBack.emit(true);
  }

}
