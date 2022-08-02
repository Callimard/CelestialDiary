import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {EmployeeManagementService} from "../../../../../service/company-management/employee/employee-management.service";
import {EmployeeDTO} from "../../../../../data/company-management/employee/employee-dto";
import {FormControl, FormGroup} from "@angular/forms";
import {EmployeeUpdatedInformation} from "../../../../../data/company-management/employee/employee-updated-information";
import {RoleFormGroup} from "../../../role-management/utils/role-form-group";
import {EmployeeRoleForm} from "../../utils/employee-role-form";

@Component({
  selector: '[app-employee-form-updater]',
  templateUrl: './employee-form-updater.component.html',
  styleUrls: ['./employee-form-updater.component.css']
})
export class EmployeeFormUpdaterComponent implements OnInit, OnChanges {

  @Input() employee!: EmployeeDTO;
  @Input() allDisable = false;

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
      password: new FormControl({value: this.defaultPassword, disabled: this.allDisable}),
      firstName: new FormControl({value: this.employee.firstName, disabled: this.allDisable}),
      lastName: new FormControl({value: this.employee.lastName, disabled: this.allDisable}),
      comment: new FormControl({value: this.employee.comment, disabled: this.allDisable}),
      gender: new FormControl({value: this.employee.gender, disabled: this.allDisable}),
      phone: new FormControl({value: this.employee.phone, disabled: this.allDisable}),
      isTechnician: new FormControl({value: String(this.employee.isTechnician), disabled: this.allDisable}),
      tags: new FormControl({value: allTagsConcat, disabled: this.allDisable}),
      roles: new EmployeeRoleForm()
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
