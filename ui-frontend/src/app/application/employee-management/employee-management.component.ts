import {Component, OnInit} from '@angular/core';
import {WrappedEmployeeDTO} from "../../../data/company-management/employee/wrapped-employee-dto";
import {EmployeeManagementService} from "../../../service/company-management/employee-management.service";
import {ScreenService} from "../../../service/screen/screen.service";

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
    return ScreenService.isBigScreen();
  }

  backToEmployeeSelection() {
    this.swipeDone = false;
    this.employeeCreation = false;
  }

  goToEmployeeCreation() {
    this.swipeDone = true;
    this.employeeCreation = true;
  }

  employeeCreationDone(success: boolean) {
    if (success) {
      this.backToEmployeeSelection();
      this.chargeEmployee()
    }
  }
}
