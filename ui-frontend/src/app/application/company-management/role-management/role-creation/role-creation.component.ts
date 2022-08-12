import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: '[app-role-creation]',
  templateUrl: './role-creation.component.html',
  styleUrls: ['./role-creation.component.css']
})
export class RoleCreationComponent implements OnInit {

  @Output() wantGoBack = new EventEmitter<boolean>();
  @Output() roleHasBeenCreated = new EventEmitter<boolean>();

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
