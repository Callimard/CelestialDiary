import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {EmployeeManagementService} from "../../../../service/company-management/employee-management.service";
import {EmployeeDTO} from "../../../../data/company-management/employee/employee-dto";
import {FormControl, FormGroup} from "@angular/forms";
import {EmployeeUpdatedInformation} from "../../../../data/company-management/employee/employee-updated-information";

@Component({
  selector: '[app-employee-information-updater]',
  templateUrl: './employee-information-updater.component.html',
  styleUrls: ['./employee-information-updater.component.css']
})
export class EmployeeInformationUpdaterComponent implements OnInit, OnChanges {

  @Input() employeeId?: string;

  employee?: EmployeeDTO;

  employeeUpdateForm?: FormGroup;

  private defaultPassword = "***************";

  updateFail = false;

  constructor(private employeeManagementService: EmployeeManagementService) {
    // Nothing.
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    this.chargeEmployee();
  }

  private chargeEmployee() {
    if (this.employeeId != null) {
      this.employeeManagementService.getSpecificEmployee(this.employeeId).then((employee) => {
        this.employee = employee;
        this.initializeEmployeeUpdateFormGroup();
      })
    }
  }

  private initializeEmployeeUpdateFormGroup() {
    let allTagsConcat: string | null = null;
    if (this.employee != null) {
      allTagsConcat = "";
      for (let tag of this.employee?.tags) {
        allTagsConcat += tag + " ";
      }
    }

    this.employeeUpdateForm = new FormGroup({
      email: new FormControl({value: this.employee?.email, disabled: true}),
      password: new FormControl(this.defaultPassword),
      firstName: new FormControl(this.employee?.firstName),
      lastName: new FormControl(this.employee?.lastName),
      comment: new FormControl(this.employee?.comment),
      gender: new FormControl(this.employee?.gender),
      phone: new FormControl(this.employee?.phone),
      isTechnician: new FormControl(String(this.employee?.isTechnician)),
      tags: new FormControl(allTagsConcat)
    });
  }

  onUpdate() {
    if (this.employeeId != null && this.employee != null) {
      const form = this.employeeUpdateForm?.value;
      const employeeUpdatedInfo: EmployeeUpdatedInformation = {
        password: form.password !== this.defaultPassword ? form.password : null,
        firstName: form.firstName,
        lastName: form.lastName,
        comment: form.comment,
        gender: form.gender,
        phone: form.phone,
        isTechnician: JSON.parse(form.isTechnician),
        tags: form.tags != null ? form.tags.split(' ') : []
      }
      console.log(employeeUpdatedInfo)
      this.employeeManagementService.updateEmployee(this.employeeId, employeeUpdatedInfo).then(() => {
        this.chargeEmployee();
        this.updateFail = false;
      }).catch(() => {
        this.updateFail = true;
      });
    }
  }

}
