import {FormControl, FormGroup} from "@angular/forms";
import {PrestationManagementService} from "../../../../service/company-management/saleable/prestation-management.service";
import {WrappedPrestationDTO} from "../../../../data/company-management/saleable/prestation/wrapped-prestation-dto";
import {EmployeeDTO} from "../../../../data/company-management/employee/employee-dto";

export class EmployeePrestationForm extends FormGroup {

  private _allPrestations: WrappedPrestationDTO[] = [];

  constructor(private prestationManagementService: PrestationManagementService, employee?: EmployeeDTO) {
    super({});

    this.prestationManagementService.allPrestations().then((allPrestations) => {
      this._allPrestations = allPrestations;
      this.createForm();
      this.mergeWithEmployee(employee);
    });
  }

  private createForm() {
    for (let prestation of this._allPrestations) {
      this.addControl(prestation.id, new FormControl(false));
    }
  }

  private mergeWithEmployee(employee?: EmployeeDTO) {
    if (employee?.praticablePrestations != null) {
      for (let prestation of employee?.praticablePrestations) {
        this.setControl(prestation.id, new FormControl(true));
      }
    }
  }

  public selectedPrestations(): string[] {
    let prestations: string[] = [];
    const controlKeys = Object.keys(this.controls);
    const v = this.value;
    for (let prestationId of controlKeys) {
      if (v[prestationId] === true) {
        prestations.push(prestationId);
      }
    }

    return prestations;
  }

  get allPrestations(): WrappedPrestationDTO[] {
    return this._allPrestations;
  }
}
