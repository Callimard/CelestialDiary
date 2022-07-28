import {Component, OnInit} from '@angular/core';
import {EmployeeDTO} from "../../../../data/company-management/employee/employee-dto";
import {Location} from "@angular/common";
import {ActivatedRoute} from "@angular/router";
import {EmployeeManagementService} from "../../../../service/company-management/employee/employee-management.service";
import {PrivilegeService} from "../../../../service/authentication/privilege.service";

@Component({
  selector: 'app-employee-information',
  templateUrl: './employee-information.component.html',
  styleUrls: ['./employee-information.component.css']
})
export class EmployeeInformationComponent implements OnInit {

  employee?: EmployeeDTO;

  constructor(private employeeManagementService: EmployeeManagementService, private location: Location, private activatedRoute: ActivatedRoute,
              private privilegeService: PrivilegeService) {
    this.activatedRoute.params.subscribe({
      next: (param) => {
        const employeeId: string | undefined = param["employeeId"];
        if (employeeId != null) {
          this.chargeEmployee(employeeId);
        }
      }
    })
  }

  ngOnInit(): void {
    // Nothing
  }

  private chargeEmployee(employeeId: string) {
    this.employeeManagementService.getSpecificEmployee(employeeId).then((employee) => {
      this.employee = employee;
    })
  }

  goBack() {
    this.location.back()
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
