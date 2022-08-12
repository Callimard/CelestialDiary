import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {WrappedEmployeeDTO} from "../../../../../data/model/person/employee/wrapped-employee-dto";
import {EmployeeManagementService} from "../../../../../service/company-management/employee/employee-management.service";
import {PrivilegeService} from "../../../../../service/authentication/privilege.service";
import {PaneInfoTransformer, PaneInfoWithId} from "../../../../library/informative/info-pane/info-pane.component";

export class EmployeePaneInfoTransformer implements PaneInfoTransformer<WrappedEmployeeDTO> {

  transform(employee: WrappedEmployeeDTO): PaneInfoWithId {
    return {
      id: employee.id,
      title: employee.firstName + ' ' + employee.lastName,
      subTitle: employee.email,
      img: employee.photo
    };
  }

}

@Component({
  selector: '[app-employee-selection]',
  templateUrl: './employee-selection.component.html',
  styleUrls: ['./employee-selection.component.css']
})
export class EmployeeSelectionComponent implements OnInit {

  @Output() employeeSelected = new EventEmitter<string>();
  @Output() wantCreateEmployee = new EventEmitter<boolean>();

  allEmployees: WrappedEmployeeDTO[] = [];

  employeePaneInfoTransformer: PaneInfoTransformer<WrappedEmployeeDTO> = new EmployeePaneInfoTransformer();

  constructor(private employeeManagementService: EmployeeManagementService,
              private privilegeService: PrivilegeService) {
    // Nothing
  }

  ngOnInit(): void {
    this.chargeEmployees();
  }

  public reload() {
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

  selectEmployee(employee: any) {
    const selectedEmployee: PaneInfoWithId = employee as PaneInfoWithId;
    this.employeeSelected.emit(selectedEmployee.id);
  }

  createEmployee() {
    this.wantCreateEmployee.emit(true);
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
