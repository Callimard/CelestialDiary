import {Component, OnInit} from '@angular/core';
import {WrappedEmployeeDTO} from "../../../../data/company-management/employee/wrapped-employee-dto";
import {ActivatedRoute, Router} from "@angular/router";
import {EmployeeManagementService} from "../../../../service/company-management/employee/employee-management.service";

@Component({
  selector: 'app-employee-selection',
  templateUrl: './employee-selection.component.html',
  styleUrls: ['./employee-selection.component.css']
})
export class EmployeeSelectionComponent implements OnInit {

  allEmployees: WrappedEmployeeDTO[] = [];

  constructor(private employeeManagementService: EmployeeManagementService, private router: Router, private activatedRoute: ActivatedRoute) {
    // Nothing
  }

  ngOnInit(): void {
    this.chargeEmployees();
  }

  private chargeEmployees() {
    this.employeeManagementService.allEmployees().then((allEmployees) => {
      this.allEmployees = allEmployees;
    })
  }

  filterEmployee(filter: string) {
    this.employeeManagementService.searchEmployee(filter).then((allEmployees) => {
      this.allEmployees = allEmployees;
    })
  }

  navigateToEmployeeInformation(product: any) {
    const selectedEmployee: WrappedEmployeeDTO = product as WrappedEmployeeDTO;
    this.router.navigate([{outlets: {right: ['information', selectedEmployee.id]}}], {
      relativeTo: this.activatedRoute
    });
  }

  navigateToCreateEmployee() {
    this.router.navigate([{outlets: {right: ['create']}}], {
      relativeTo: this.activatedRoute
    });
  }

}
