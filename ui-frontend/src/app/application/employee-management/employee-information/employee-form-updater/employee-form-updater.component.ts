import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {EmployeeManagementService} from "../../../../../service/company-management/employee/employee-management.service";
import {EmployeeDTO} from "../../../../../data/company-management/person/employee/employee-dto";
import {EmployeeUpdatedInformation} from "../../../../../data/company-management/person/employee/employee-updated-information";
import {RoleFormGroup} from "../../../role-management/utils/role-form-group";
import {EmployeeForm} from "../../utils/employee-form";
import {PrestationManagementService} from "../../../../../service/company-management/saleable/prestation-management.service";
import {RoleManagementService} from "../../../../../service/company-management/employee/role/role-management.service";

@Component({
  selector: '[app-employee-form-updater]',
  templateUrl: './employee-form-updater.component.html',
  styleUrls: ['./employee-form-updater.component.css']
})
export class EmployeeFormUpdaterComponent implements OnInit, OnChanges {

  @Input() employee!: EmployeeDTO;
  @Input() allDisable = false;

  employeeUpdateForm!: EmployeeForm;

  updateFail = false;

  constructor(private prestationManagementService: PrestationManagementService,
              private roleManagementService: RoleManagementService,
              private employeeManagementService: EmployeeManagementService) {
    // Nothing.
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    this.initializeEmployeeUpdateFormGroup()
  }

  private initializeEmployeeUpdateFormGroup() {
    this.employeeUpdateForm = new EmployeeForm(this.prestationManagementService, this.roleManagementService, false, this.employee);

    if (this.allDisable) {
      this.employeeUpdateForm.disable();
    }
  }

  onUpdate() {
    if (this.employee != null) {
      const form = this.employeeUpdateForm?.value;
      const employeeUpdatedInfo: EmployeeUpdatedInformation = {
        password: form.password !== EmployeeForm.DEFAULT_PASSWORD ? form.password : null,
        firstName: form.firstName,
        lastName: form.lastName,
        comment: form.comment,
        gender: form.gender,
        phone: form.phone,
        praticablePrestations: this.employeeUpdateForm.prestationFormGroup.selectedPrestations(),
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
