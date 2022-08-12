import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: '[app-employee-creation]',
  templateUrl: './employee-creation.component.html',
  styleUrls: ['./employee-creation.component.css']
})
export class EmployeeCreationComponent implements OnInit {

  @Output() employeeHasBeenCreated = new EventEmitter<boolean>();
  @Output() wantGoBack = new EventEmitter<boolean>();

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // nothing
  }

  goBack(success: boolean) {
    if (success)
      this.wantGoBack.emit(true);
  }

}
