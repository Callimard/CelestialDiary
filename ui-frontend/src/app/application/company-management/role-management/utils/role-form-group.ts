import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ScopeFormGroup} from "./scope-form-group";
import {ScopeDTO} from "../../../../../data/security/privilege/scope-dto";
import {RoleDTO} from "../../../../../data/model/person/employee/role/role-dto";
import {PrivilegeManagementService} from "../../../../../service/security/privilege/privilege-management.service";
import {EstablishmentManagementService} from "../../../../../service/company-management/establishment/establishment-management.service";
import {EstablishmentRoleFormGroup} from "./establishment-role-form-group";
import {EstablishmentRoleDTO} from "../../../../../data/model/person/employee/role/establishment-role-dto";
import {RoleCreationInformation} from "../../../../../data/model/person/employee/role/role-creation-information";
import {RoleUpdatedInformation} from "../../../../../data/model/person/employee/role/role-updated-information";

export class RoleFormGroup extends FormGroup {

  companyManagementScope?: ScopeDTO;
  internEstablishmentManagementScope?: ScopeDTO;

  constructor(private privilegeManagementService: PrivilegeManagementService, private establishmentManagementService: EstablishmentManagementService,
              withValidators = false, role?: RoleDTO) {
    super({
      name: new FormControl(role?.name, withValidators ? [Validators.required] : []),
      description: new FormControl(role?.description),
      establishments: new FormGroup({})
    });

    this.chargeCompanyManagementScope(role);
    this.chargeInternEstablishmentManagementScope(role);
  }

  private chargeCompanyManagementScope(role?: RoleDTO) {
    this.privilegeManagementService.companyManagementScope().then((scope) => {
      this.companyManagementScope = scope;
      this.addControl('companyManagementScope', new ScopeFormGroup(this.companyManagementScope, role?.companyPrivileges));
    });
  }

  private chargeInternEstablishmentManagementScope(role?: RoleDTO) {
    this.privilegeManagementService.internEstablishmentManagementScope().then((scope) => {
      this.internEstablishmentManagementScope = scope;
      this.establishmentManagementService.allEstablishments().then((allEstablishments) => {
        if (this.internEstablishmentManagementScope != null) {
          for (let establishment of allEstablishments) {
            this.establishments.addControl(establishment.id,
              new EstablishmentRoleFormGroup(establishment,
                this.internEstablishmentManagementScope,
                role != null ? RoleFormGroup.searchEstablishmentRoleFor(establishment.id, role?.establishmentRoles)?.establishmentPrivileges : undefined));
          }
        }
      })
    })
  }

  private static searchEstablishmentRoleFor(establishmentId: string, establishmentRoles: EstablishmentRoleDTO[]): EstablishmentRoleDTO | undefined {
    for (let establishmentRole of establishmentRoles) {
      if (establishmentRole.establishmentId === establishmentId) {
        return establishmentRole;
      }
    }
    return undefined;
  }

  public extractRoleInformation(): RoleCreationInformation | RoleUpdatedInformation {
    return {
      name: this.value.name,
      description: this.value.description,
      companyPrivilegeIdentifiers: this.companyManagementScopeFormGroup.getSelectedPrivileges(),
      establishmentRoles: this.extractEstablishmentRoleMap()
    }
  }

  private extractEstablishmentRoleMap(): { [establishmentId: string]: string[] } {
    let res: { [establishmentId: string]: string[] } = {};

    const keys: string[] = Object.keys(this.establishments.controls);
    for (let key of keys) {
      const establishmentRoleForm: EstablishmentRoleFormGroup = this.establishments.get(key) as EstablishmentRoleFormGroup;
      res[key] = establishmentRoleForm.getSelectedPrivileges();
    }

    return res;
  }

  get companyManagementScopeFormGroup(): ScopeFormGroup {
    return this.get('companyManagementScope') as ScopeFormGroup;
  }

  get establishments(): FormGroup {
    return this.get('establishments') as FormGroup;
  }

  get allEstablishmentIds(): string[] {
    return Object.keys(this.establishments.controls);
  }

  getEstablishmentRoleForm(establishmentId: string): EstablishmentRoleFormGroup {
    return this.establishments.get(establishmentId) as EstablishmentRoleFormGroup;
  }
}
