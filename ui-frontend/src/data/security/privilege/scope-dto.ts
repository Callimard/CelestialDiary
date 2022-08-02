import {PrivilegeDTO} from "./privilege-dto";

export interface ScopeDTO {
  name: string,
  description: string,
  privileges: PrivilegeDTO[],
  scopeChildren: ScopeDTO[]
}
