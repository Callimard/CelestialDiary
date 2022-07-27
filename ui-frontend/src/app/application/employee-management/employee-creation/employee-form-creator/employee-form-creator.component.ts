import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {EmployeeManagementService} from "../../../../../service/company-management/employee/employee-management.service";
import {EmployeeCreationInformation} from "../../../../../data/company-management/employee/employee-creation-information";
import {RoleFormGroup} from "../../../../../service/company-management/employee/role/role-form-group";

@Component({
  selector: '[app-employee-form-creator]',
  templateUrl: './employee-form-creator.component.html',
  styleUrls: ['./employee-form-creator.component.css']
})
export class EmployeeFormCreatorComponent implements OnInit {

  creationFailed = false;

  @Output() employeeCreated = new EventEmitter<boolean>();

  employeeCreationForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.pattern("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z0-9@$!%*?&]{8,30}$")]),
    firstName: new FormControl('', [Validators.required]),
    lastName: new FormControl('', [Validators.required]),
    comment: new FormControl(''),
    gender: new FormControl('', [Validators.required]),
    phone: new FormControl('', [Validators.required]),
    isTechnician: new FormControl('', [Validators.required]),
    tags: new FormControl(''),
    roles: new RoleFormGroup()
  })

  constructor(private employeeManagementService: EmployeeManagementService) {
    // Nothing.
  }

  ngOnInit(): void {
    // Nothing
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
      isTechnician: JSON.parse(form.isTechnician),
      tags: form.tags != null && form.tags.lenght > 0 ? form.tags.split(' ') : [],
      roles: this.roleFormGroup.extractRoles()
    }

    this.employeeManagementService.createEmployee(employeeInfo).then(() => {
      this.employeeCreated.emit(true);
      this.creationFailed = false
    }).catch(() => {
      this.employeeCreated.emit(false);
      this.creationFailed = true;
    });
  }

  get roleFormGroup(): RoleFormGroup {
    return this.employeeCreationForm.get('roles') as RoleFormGroup;
  }

}
