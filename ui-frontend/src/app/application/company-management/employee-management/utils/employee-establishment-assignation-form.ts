import {FormControl, FormGroup} from "@angular/forms";
import {EstablishmentManagementService} from "../../../../../service/company-management/establishment/establishment-management.service";
import {WrappedEstablishmentDTO} from "../../../../../data/model/establishment/wrapped-establishment-dto";
import {EmployeeDTO} from "../../../../../data/model/person/employee/employee-dto";

export class EmployeeEstablishmentAssignationForm extends FormGroup {

  _allEstablishment: WrappedEstablishmentDTO[] = []

  constructor(private establishmentManagementService: EstablishmentManagementService, employee?: EmployeeDTO) {
    super({});
    this.initEstablishmentControl(employee);
  }

  private initEstablishmentControl(employee?: EmployeeDTO) {
    this.establishmentManagementService.allEstablishments().then((allEstablishments) => {
      this._allEstablishment = allEstablishments;

      this.addEstablishmentControl();
      this.mergeWithEmployee(employee);
    })
  }

  private addEstablishmentControl() {
    for (let establishment of this._allEstablishment) {
      this.addControl(establishment.id, new FormControl(false));
    }
  }

  private mergeWithEmployee(employee?: EmployeeDTO) {
    if (employee?.assignedEstablishments != null) {
      for (let establishment of employee.assignedEstablishments) {
        this.setControl(establishment.id, new FormControl(true));
      }
    }
  }

  public selectedEstablishments(): string[] {
    let establishments: string[] = [];
    const controlKeys = Object.keys(this.controls);
    const v = this.value;
    for (let establishmentId of controlKeys) {
      if (v[establishmentId] === true) {
        establishments.push(establishmentId);
      }
    }

    return establishments;
  }

  get allEstablishment(): WrappedEstablishmentDTO[] {
    return this._allEstablishment;
  }
}
