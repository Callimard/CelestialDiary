import { Component, OnInit } from '@angular/core';
import {Location} from "@angular/common";

@Component({
  selector: 'app-employee-creation',
  templateUrl: './employee-creation.component.html',
  styleUrls: ['./employee-creation.component.css']
})
export class EmployeeCreationComponent implements OnInit {

  constructor(private location: Location) {
    // Nothing
  }

  ngOnInit(): void {
    // nothing
  }

  goBack() {
    this.location.back();
  }

}
