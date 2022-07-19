import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {EmployeeManagementService} from "../../../../service/company-management/employee-management.service";
import {EmployeeCreationInformation} from "../../../../data/company-management/employee/employee-creation-information";

@Component({
  selector: '[app-employee-creator]',
  templateUrl: './employee-creator.component.html',
  styleUrls: ['./employee-creator.component.css']
})
export class EmployeeCreatorComponent implements OnInit {

  employeeCreationFailed = false;

  @Output() creationSuccess = new EventEmitter<boolean>();

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
    roles: new FormControl('')
  })

  constructor(private employeeManagementService: EmployeeManagementService) {
    // Nothing.
  }

  ngOnInit(): void {
    // Nothing.
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
      roles: []
    }
    this.employeeManagementService.createEmployee(employeeInfo).then(() => {
      this.creationSuccess.emit(true);
      this.employeeCreationFailed = false
    }).catch(() => {
      this.creationSuccess.emit(false);
      this.employeeCreationFailed = true;
    });
  }

}
