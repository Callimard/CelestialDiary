import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {EmployeeDTO} from "../../../../../data/company-management/employee/employee-dto";
import {EmployeeManagementService} from "../../../../../service/company-management/employee/employee-management.service";
import {EstablishmentManagementService} from "../../../../../service/company-management/establishment/establishment-management.service";
import {EmployeeEstablishmentAssignationForm} from "../../utils/employee-establishment-assignation-form";
import {EmployeeEstablishmentInformation} from "../../../../../data/company-management/employee/employee-establishment-information";

@Component({
  selector: '[app-employee-establishment-assignation]',
  templateUrl: './employee-establishment-assignation.component.html',
  styleUrls: ['./employee-establishment-assignation.component.css']
})
export class EmployeeEstablishmentAssignationComponent implements OnInit, OnChanges {

  @Input() employee!: EmployeeDTO;

  establishmentSelectionForm!: EmployeeEstablishmentAssignationForm;

  constructor(private employeeManagementService: EmployeeManagementService, private establishmentManagementService: EstablishmentManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    this.establishmentSelectionForm = new EmployeeEstablishmentAssignationForm(this.establishmentManagementService, this.employee);
  }

  onUpdateEmployeeEstablishment() {
    const update: EmployeeEstablishmentInformation = {
      establishmentIds: this.establishmentSelectionForm.selectedEstablishments()
    }

    this.employeeManagementService.updateEmployeeEstablishmentAssignation(this.employee.id, update).then((employee) => {
      this.employee = employee;
    })
  }

}
