import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {EmployeeDTO} from "../../../../../../../data/model/person/employee/employee-dto";
import {WrappedEstablishmentDTO} from "../../../../../../../data/model/establishment/wrapped-establishment-dto";
import {EmployeeManagementService} from "../../../../../../../service/company-management/employee/employee-management.service";
import {WorkingHoursFormGroup} from "../../../utils/working-hours-form-group";
import {DatePipe} from "@angular/common";

@Component({
  selector: '[app-employee-establishment-working-hours]',
  templateUrl: './employee-establishment-working-hours.component.html',
  styleUrls: ['./employee-establishment-working-hours.component.css']
})
export class EmployeeEstablishmentWorkingHoursComponent implements OnInit, OnChanges {

  @Input() employee!: EmployeeDTO;
  @Input() establishment!: WrappedEstablishmentDTO;
  @Input() allDisabled = false;

  currentDate = new Date();

  workingHoursForm?: WorkingHoursFormGroup;

  updateFailed = false;

  constructor(private employeeManagementService: EmployeeManagementService, private datePipe: DatePipe) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges) {
    this.chargeWorkingHours();
  }

  private chargeWorkingHours() {
    this.employeeManagementService.getEmployeeWorkingHours(this.employee.id, this.establishment.id,
      this.currentDate.getFullYear(), this.currentDateWeekNumber()).then((workingHours) => {
      this.workingHoursForm = new WorkingHoursFormGroup(workingHours);

      if (this.allDisabled) {
        this.workingHoursForm.disable();
      }
    });
  }

  private currentDateWeekNumber(): number {
    const w: string = this.datePipe.transform(this.currentDate, 'w') as string;
    return +w;
  }

  onUpdate() {
    if (this.workingHoursForm != null) {
      const currentYear = this.currentDate.getFullYear();
      const currentWeekNumber = this.currentDateWeekNumber();

      this.employeeManagementService.updateEmployeeWorkingHours(this.employee.id, this.establishment.id,
        this.workingHoursForm?.toWorkingHoursInformation(currentYear, currentWeekNumber)).then(() => {
        this.updateFailed = false;
      }).catch(() => {
        this.updateFailed = true;
      })
    }
  }

  weekChange(weekDate: Date) {
    this.currentDate = weekDate;
    this.chargeWorkingHours();
  }

}
