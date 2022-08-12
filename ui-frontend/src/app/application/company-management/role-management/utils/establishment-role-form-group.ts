import {WrappedEstablishmentDTO} from "../../../../../data/model/establishment/wrapped-establishment-dto";
import {ScopeFormGroup} from "./scope-form-group";
import {ScopeDTO} from "../../../../../data/security/privilege/scope-dto";
import {PrivilegeDTO} from "../../../../../data/security/privilege/privilege-dto";

export class EstablishmentRoleFormGroup extends ScopeFormGroup {

  private readonly _establishment: WrappedEstablishmentDTO;

  constructor(establishment: WrappedEstablishmentDTO, scope: ScopeDTO, privileges?: PrivilegeDTO[]) {
    super(scope, privileges);
    this._establishment = establishment;
  }

  get establishment(): WrappedEstablishmentDTO {
    return this._establishment;
  }
}
