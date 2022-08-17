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

  public hasClientAll(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasCompanyAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.client.all);
  }

  public hasClientRead(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasClientAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.client.read);
  }

  public hasClientCreate(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasClientAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.client.create);
  }

  public hasClientUpdate(): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasClientAll() || jwtAccount.companyPrivilegeIdentifiers.includes(privileges.company.client.update);
  }

  public hasSomeClientPrivileges(): boolean {
    return this.hasClientAll() || this.hasClientRead() || this.hasClientCreate() || this.hasClientUpdate()
  }

  public hasInternEstablishmentAll(establishmentId: string): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasCompanyAll() || jwtAccount.establishmentPrivilegeIdentifiers[establishmentId].includes(privileges.establishment.all)
  }

  public hasRoomAll(establishmentId: string): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasInternEstablishmentAll(establishmentId) || jwtAccount.establishmentPrivilegeIdentifiers[establishmentId].includes(privileges.establishment.room.all);
  }

  public hasRoomRead(establishmentId: string): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasRoomAll(establishmentId) || jwtAccount.establishmentPrivilegeIdentifiers[establishmentId].includes(privileges.establishment.room.read);
  }

  public hasRoomCreate(establishmentId: string): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasRoomAll(establishmentId) || jwtAccount.establishmentPrivilegeIdentifiers[establishmentId].includes(privileges.establishment.room.create);
  }

  public hasRoomUpdate(establishmentId: string): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasRoomAll(establishmentId) || jwtAccount.establishmentPrivilegeIdentifiers[establishmentId].includes(privileges.establishment.room.update);
  }

  public hasRoomDelete(establishmentId: string): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasRoomAll(establishmentId) || jwtAccount.establishmentPrivilegeIdentifiers[establishmentId].includes(privileges.establishment.room.delete);
  }

  public hasSomeRoomPrivileges(establishmentId: string): boolean {
    return this.hasRoomAll(establishmentId) || this.hasRoomRead(establishmentId) || this.hasRoomCreate(establishmentId)
      || this.hasRoomUpdate(establishmentId) || this.hasRoomDelete(establishmentId);
  }

  public hasEstablishmentEquipmentAll(establishmentId: string): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasInternEstablishmentAll(establishmentId) || jwtAccount.establishmentPrivilegeIdentifiers[establishmentId].includes(privileges.establishment.equipment.all);
  }

  public hasEstablishmentEquipmentRead(establishmentId: string): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasEstablishmentEquipmentAll(establishmentId) || jwtAccount.establishmentPrivilegeIdentifiers[establishmentId].includes(privileges.establishment.equipment.read);
  }

  public hasEstablishmentEquipmentAdd(establishmentId: string): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasEstablishmentEquipmentAll(establishmentId) || jwtAccount.establishmentPrivilegeIdentifiers[establishmentId].includes(privileges.establishment.equipment.add);
  }

  public hasEstablishmentEquipmentUpdate(establishmentId: string): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasEstablishmentEquipmentAll(establishmentId) || jwtAccount.establishmentPrivilegeIdentifiers[establishmentId].includes(privileges.establishment.equipment.update);
  }

  public hasEstablishmentEquipmentDelete(establishmentId: string): boolean {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.hasEstablishmentEquipmentAll(establishmentId) || jwtAccount.establishmentPrivilegeIdentifiers[establishmentId].includes(privileges.establishment.equipment.delete);
  }

  public hasSomeEstablishmentEquipmentPrivileges(establishmentId: string): boolean {
    return this.hasEstablishmentEquipmentAll(establishmentId) || this.hasEstablishmentEquipmentRead(establishmentId) || this.hasEstablishmentEquipmentAdd(establishmentId)
      || this.hasEstablishmentEquipmentUpdate(establishmentId) || this.hasEstablishmentEquipmentDelete(establishmentId);
  }

}
