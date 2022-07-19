import {Component, OnInit} from '@angular/core';
import {WrappedEmployeeDTO} from "../../../data/company-management/employee/wrapped-employee-dto";
import {EmployeeManagementService} from "../../../service/company-management/employee-management.service";
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-employee-management',
  templateUrl: './employee-management.component.html',
  styleUrls: ['./employee-management.component.css']
})
export class EmployeeManagementComponent implements OnInit {

  allEmployees: WrappedEmployeeDTO[] = [];

  selectedEmployee?: WrappedEmployeeDTO;

  swipeDone = false;

  employeeCreation = false;

  constructor(private employeeManagementService: EmployeeManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    this.chargeEmployee()
  }

  chargeEmployee() {
    this.employeeManagementService.allEmployees().then((employees) => {
      this.allEmployees = employees;
    })
  }

  searchText(text: string) {
    this.employeeManagementService.searchEmployee(text).then((employees) => {
      this.allEmployees = employees;
    })
  }

  selectEmployee(employee: WrappedEmployeeDTO) {
    this.selectedEmployee = employee;
    this.swipeDone = true;
    this.employeeCreation = false;
  }

  isBigScreen(): boolean {
    return window.innerWidth > environment.bigScreenWidth;
  }

  backToEmployeeSelection() {
    this.swipeDone = false;
    this.employeeCreation = false;
  }

  createEmployee() {
    this.swipeDone = true;
    this.employeeCreation = true;
  }

  creationEmployeeDone(success: boolean) {
    if (success) {
      this.backToEmployeeSelection();
      this.chargeEmployee()
    }
  }
}
