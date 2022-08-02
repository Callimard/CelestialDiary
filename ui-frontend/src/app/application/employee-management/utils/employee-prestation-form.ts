import {FormControl, FormGroup} from "@angular/forms";
import {PrestationManagementService} from "../../../../service/company-management/saleable/prestation-management.service";
import {WrappedPrestationDTO} from "../../../../data/company-management/saleable/prestation/wrapped-prestation-dto";

export class EmployeePrestationForm extends FormGroup {

  private _allPrestations: WrappedPrestationDTO[] = [];

  constructor(private prestationManagementService: PrestationManagementService) {
    super({});

    this.prestationManagementService.allPrestations().then((allPrestations) => {
      this._allPrestations = allPrestations;
      for (let prestation of this._allPrestations) {
        this.addControl(prestation.id, new FormControl(false));
      }
    });
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
