import {Injectable} from '@angular/core';
import {AuthenticationService} from "./authentication.service";
import {JwtAccount} from "./jwt-account";
import {privileges} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class PrivilegeService {

  constructor() {
    // Nothing
  }

  public hasCompanyAll(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.all);
  }

  public hasEmployeeAll(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasCompanyAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.employee.all);
  }

  public hasEmployeeRead(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasEmployeeAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.employee.read);
  }

  public hasEmployeeCreate(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasEmployeeAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.employee.create);
  }

  public hasEmployeeUpdate(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasEmployeeAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.employee.update);
  }

  public hasEmployeeUpdateRole(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasEmployeeAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.employee.update_role);
  }

  public hasEmployeeAssign(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasEmployeeAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.employee.assign);
  }

  public hasEmployeeActivate(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasEmployeeAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.employee.activate);
  }

  public hasSomeEmployeePrivileges(): boolean {
    return this.hasEmployeeAll() || this.hasEmployeeRead() || this.hasEmployeeCreate() || this.hasEmployeeUpdate() || this.hasEmployeeUpdateRole()
      || this.hasEmployeeAssign() || this.hasEmployeeActivate();
  }

  public hasEstablishmentAll(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasCompanyAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.establishment.all);
  }

  public hasEstablishmentRead(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasEstablishmentAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.establishment.read);
  }

  public hasEstablishmentCreate(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasEstablishmentAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.establishment.create);
  }

  public hasEstablishmentUpdate(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasEstablishmentAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.establishment.update);
  }

  public hasEstablishmentActivate(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasEstablishmentAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.establishment.activate);
  }

  public hasSomeEstablishmentPrivileges(): boolean {
    return this.hasEstablishmentAll() || this.hasEstablishmentRead() || this.hasEstablishmentCreate() || this.hasEstablishmentUpdate()
      || this.hasEstablishmentActivate()
  }

  public hasSaleableAll(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasCompanyAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.saleable.all);
  }

  public hasSaleableRead(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasSaleableAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.saleable.read);
  }

  public hasSaleableCreate(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasSaleableAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.saleable.create);
  }

  public hasSaleableUpdate(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasSaleableAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.saleable.update);
  }

  public hasSaleableActivate(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasSaleableAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.saleable.activate);
  }

  public hasSomeSaleablePrivileges(): boolean {
    return this.hasSaleableAll() || this.hasSaleableRead() || this.hasSaleableCreate() || this.hasSaleableUpdate()
      || this.hasSaleableActivate()
  }

  public hasRoleAll(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasCompanyAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.role.all);
  }

  public hasRoleRead(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasRoleAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.role.read);
  }

  public hasRoleCreate(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasRoleAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.role.create);
  }

  public hasRoleUpdate(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasRoleAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.role.update);
  }

  public hasRoleDelete(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasRoleAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.role.delete);
  }

  public hasSomeRolePrivileges(): boolean {
    return this.hasRoleAll() || this.hasRoleRead() || this.hasRoleCreate() || this.hasRoleUpdate()
      || this.hasRoleDelete()
  }

  public hasEquipmentAll(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasCompanyAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.equipment.all);
  }

  public hasEquipmentRead(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasEquipmentAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.equipment.read);
  }

  public hasEquipmentCreate(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasEquipmentAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.equipment.create);
  }

  public hasEquipmentUpdate(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasEquipmentAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.equipment.update);
  }

  public hasEquipmentDelete(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasEquipmentAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.equipment.delete);
  }

  public hasSomeEquipmentPrivileges(): boolean {
    return this.hasEquipmentAll() || this.hasEquipmentRead() || this.hasEquipmentCreate() || this.hasEquipmentUpdate()
      || this.hasEquipmentDelete()
  }

}
