import {Component, Input, OnInit} from '@angular/core';
import {EmployeeDTO} from "../../../../../data/company-management/employee/employee-dto";
import {EmployeeManagementService} from "../../../../../service/company-management/employee/employee-management.service";
import {EstablishmentManagementService} from "../../../../../service/company-management/establishment/establishment-management.service";
import {EmployeeEstablishmentAssignationForm} from "../../utils/employee-establishment-assignation-form";

@Component({
  selector: '[app-employee-establishment-assignation]',
  templateUrl: './employee-establishment-assignation.component.html',
  styleUrls: ['./employee-establishment-assignation.component.css']
})
export class EmployeeEstablishmentAssignationComponent implements OnInit {

  @Input() employee!: EmployeeDTO;

  establishmentSelectionForm!: EmployeeEstablishmentAssignationForm;

  constructor(private employeeManagementService: EmployeeManagementService, private establishmentManagementService: EstablishmentManagementService) {
    this.establishmentSelectionForm = new EmployeeEstablishmentAssignationForm(establishmentManagementService, this.employee);
  }

  ngOnInit(): void {
    // Nothing
  }

  onUpdateEmployeeEstablishment() {
    const selectedEstablishments: string[] = this.establishmentSelectionForm.selectedEstablishments();

    for (let establishmentId of selectedEstablishments) {
      // TODO
    }
  }

}
