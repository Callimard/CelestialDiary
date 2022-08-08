import {Component, OnInit} from '@angular/core';
import {WrappedEmployeeDTO} from "../../../../data/company-management/person/employee/wrapped-employee-dto";
import {ActivatedRoute, Router} from "@angular/router";
import {EmployeeManagementService} from "../../../../service/company-management/employee/employee-management.service";
import {PrivilegeService} from "../../../../service/authentication/privilege.service";
import {PaneInfo, PaneInfoTransformer, PaneInfoWithId} from "../../../libairy/informative/info-pane/info-pane.component";

export interface EmployeePaneInfo extends PaneInfo {
  employeeId: string
}

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
  selector: 'app-employee-selection',
  templateUrl: './employee-selection.component.html',
  styleUrls: ['./employee-selection.component.css']
})
export class EmployeeSelectionComponent implements OnInit {

  allEmployees: WrappedEmployeeDTO[] = [];

  employeePaneInfoTransformer: PaneInfoTransformer<WrappedEmployeeDTO> = new EmployeePaneInfoTransformer();

  constructor(private employeeManagementService: EmployeeManagementService, private router: Router, private activatedRoute: ActivatedRoute,
              private privilegeService: PrivilegeService) {
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

  navigateToEmployeeInformation(employee: any) {
    const selectedEmployee: PaneInfoWithId = employee as PaneInfoWithId;
    this.router.navigate([{outlets: {right: ['information', selectedEmployee.id]}}], {
      relativeTo: this.activatedRoute
    });
  }

  navigateToCreateEmployee() {
    this.router.navigate([{outlets: {right: ['create']}}], {
      relativeTo: this.activatedRoute
    });
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
