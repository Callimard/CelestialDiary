import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {EmployeeDTO} from "../../../../data/company-management/person/employee/employee-dto";
import {EmployeeManagementService} from "../../../../service/company-management/employee/employee-management.service";
import {PrivilegeService} from "../../../../service/authentication/privilege.service";

@Component({
  selector: '[app-employee-information]',
  templateUrl: './employee-information.component.html',
  styleUrls: ['./employee-information.component.css']
})
export class EmployeeInformationComponent implements OnInit, OnChanges {

  @Input() employeeId?: string;

  @Output() wantGoBack = new EventEmitter<boolean>();
  @Output() employeeHasBeenUpdated = new EventEmitter<boolean>();

  employee?: EmployeeDTO;

  constructor(private employeeManagementService: EmployeeManagementService,
              private privilegeService: PrivilegeService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    if (this.employeeId != null)
      this.chargeEmployee(this.employeeId);
  }

  private chargeEmployee(employeeId: string) {
    this.employeeManagementService.getSpecificEmployee(employeeId).then((employee) => {
      this.employee = employee;
    })
  }

  goBack(success: boolean) {
    if (success)
      this.wantGoBack.emit(true);
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
