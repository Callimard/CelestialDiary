import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {EmployeeManagementService} from "../../../../../service/company-management/employee-management.service";
import {EmployeeDTO} from "../../../../../data/company-management/employee/employee-dto";
import {FormControl, FormGroup} from "@angular/forms";
import {EmployeeUpdatedInformation} from "../../../../../data/company-management/employee/employee-updated-information";
import {RoleFormGroup} from "../../../../../service/company-management/role-form-group";

@Component({
  selector: '[app-employee-form-updater]',
  templateUrl: './employee-form-updater.component.html',
  styleUrls: ['./employee-form-updater.component.css']
})
export class EmployeeFormUpdaterComponent implements OnInit, OnChanges {

  @Input() employee!: EmployeeDTO;

  employeeUpdateForm!: FormGroup;

  private defaultPassword = "***************";

  updateFail = false;

  constructor(private employeeManagementService: EmployeeManagementService) {
    // Nothing.
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    this.initializeEmployeeUpdateFormGroup()
  }

  private initializeEmployeeUpdateFormGroup() {
    let allTagsConcat = this.extractEmployeeTags();
    this.employeeUpdateForm = this.createEmployeeForm(allTagsConcat);
  }

  private extractEmployeeTags() {
    let allTagsConcat: string | null = null;
    if (this.employee != null) {
      allTagsConcat = "";
      for (let tag of this.employee.tags) {
        allTagsConcat += tag + " ";
      }
    }
    return allTagsConcat;
  }

  private createEmployeeForm(allTagsConcat: null | string) {
    return new FormGroup({
      email: new FormControl({value: this.employee.email, disabled: true}),
      password: new FormControl(this.defaultPassword),
      firstName: new FormControl(this.employee.firstName),
      lastName: new FormControl(this.employee.lastName),
      comment: new FormControl(this.employee.comment),
      gender: new FormControl(this.employee.gender),
      phone: new FormControl(this.employee.phone),
      isTechnician: new FormControl(String(this.employee.isTechnician)),
      tags: new FormControl(allTagsConcat),
      roles: new RoleFormGroup()
    });
  }

  onUpdate() {
    if (this.employee != null) {
      const form = this.employeeUpdateForm?.value;
      const employeeUpdatedInfo: EmployeeUpdatedInformation = {
        password: form.password !== this.defaultPassword ? form.password : null,
        firstName: form.firstName,
        lastName: form.lastName,
        comment: form.comment,
        gender: form.gender,
        phone: form.phone,
        isTechnician: JSON.parse(form.isTechnician),
        tags: (form.tags != null ? form.tags.split(' ') : []),
      }

      this.employeeManagementService.updateEmployee(this.employee.id, employeeUpdatedInfo).then((employee) => {
        this.updateFail = false;
        this.employee = employee;
      }).catch(() => {
        this.updateFail = true;
      });
    }
  }



  get roleFormGroup(): RoleFormGroup {
    return this.employeeUpdateForm.get('roles') as RoleFormGroup;
  }
}
