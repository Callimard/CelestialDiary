import {Component, Input, OnInit} from '@angular/core';
import {EmployeeDTO} from "../../../../../data/company-management/person/employee/employee-dto";

@Component({
  selector: '[app-employee-working-hours-updater]',
  templateUrl: './employee-working-hours-updater.component.html',
  styleUrls: ['./employee-working-hours-updater.component.css']
})
export class EmployeeWorkingHoursUpdaterComponent implements OnInit {

  @Input() employee!: EmployeeDTO;
  @Input() allDisable = false;

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

}
