import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {EmployeeManagementService} from "../../../../../../service/company-management/employee/employee-management.service";
import {EmployeeCreationInformation} from "../../../../../../data/model/person/employee/employee-creation-information";
import {EmployeeForm} from "../../utils/employee-form";
import {PrestationManagementService} from "../../../../../../service/company-management/saleable/prestation-management.service";
import {RoleManagementService} from "../../../../../../service/company-management/employee/role/role-management.service";

@Component({
  selector: '[app-employee-form-creator]',
  templateUrl: './employee-form-creator.component.html',
  styleUrls: ['./employee-form-creator.component.css']
})
export class EmployeeFormCreatorComponent implements OnInit {

  creationFailed = false;

  @Output() employeeCreated = new EventEmitter<boolean>();

  employeeCreationForm!: EmployeeForm;

  constructor(private prestationManagementService: PrestationManagementService,
              private roleManagementService: RoleManagementService,
              private employeeManagementService: EmployeeManagementService) {
    // Nothing.
  }

  ngOnInit(): void {
    this.employeeCreationForm = new EmployeeForm(this.prestationManagementService, this.roleManagementService);
  }

  onEmployeeCreation() {
    const form = this.employeeCreationForm.value;
    const employeeInfo: EmployeeCreationInformation = {
      email: form.email,
      password: form.password,
      firstName: form.firstName,
      lastName: form.lastName,
      comment: form.comment,
      gender: form.gender,
      phone: form.phone,
      tags: form.tags != null && form.tags.lenght > 0 ? form.tags.split(' ') : [],
      praticablePrestations: this.employeeCreationForm.prestationFormGroup.selectedPrestations(),
      roles: this.employeeCreationForm.roleFormGroup.selectedRoles()
    }

    console.log(employeeInfo);

    this.employeeManagementService.createEmployee(employeeInfo).then(() => {
      this.employeeCreated.emit(true);
      this.creationFailed = false
    }).catch(() => {
      this.employeeCreated.emit(false);
      this.creationFailed = true;
    });
  }
}
