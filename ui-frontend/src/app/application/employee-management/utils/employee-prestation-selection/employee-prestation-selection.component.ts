import {Component, Input, OnInit} from '@angular/core';
import {EmployeePrestationForm} from "../employee-prestation-form";
import {PrestationDTO} from "../../../../../data/company-management/saleable/prestation/prestation-dto";

@Component({
  selector: '[app-employee-prestation-selection]',
  templateUrl: './employee-prestation-selection.component.html',
  styleUrls: ['./employee-prestation-selection.component.css']
})
export class EmployeePrestationSelectionComponent implements OnInit {

  @Input() employeePrestationForm!: EmployeePrestationForm;

  allPrestations: PrestationDTO[] = [];

  constructor() {
    // Nothing.
  }

  ngOnInit(): void {
    // Nothing.
  }
}
