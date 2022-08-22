import {Address} from "../../general/address";
import {NonDatedTimeIntervalListDTO} from "../../general/time/non-dated-time-interval-list-dto";
import {DatedTimeIntervalListDTO} from "../../general/time/dated-time-interval-list-dto";
import {EstablishmentProductDTO} from "./establishment-product-dto";
import {EstablishmentPrestationDTO} from "./establishment-prestation-dto";
import {WrappedEmployeeDTO} from "../person/employee/wrapped-employee-dto";
import {EstablishmentEquipmentDTO} from "./establishment-equipment-dto";
import {EstablishmentBundleDTO} from "./establishment-bundle-dto";

export interface EstablishmentDTO {
  id: string,
  name: string,
  description?: string,
  address: Address,
  mondayOpening: NonDatedTimeIntervalListDTO,
  tuesdayOpening: NonDatedTimeIntervalListDTO,
  wednesdayOpening: NonDatedTimeIntervalListDTO,
  thursdayOpening: NonDatedTimeIntervalListDTO,
  fridayOpening: NonDatedTimeIntervalListDTO,
  saturdayOpening: NonDatedTimeIntervalListDTO,
  sundayOpening: NonDatedTimeIntervalListDTO,
  exceptionalOpening: DatedTimeIntervalListDTO[],
  exceptionalClosing: DatedTimeIntervalListDTO[],
  proposedProducts: EstablishmentProductDTO[],
  proposedServices: EstablishmentPrestationDTO[],
  proposedBundles: EstablishmentBundleDTO[],
  equipments: EstablishmentEquipmentDTO[],
  assignedEmployees: WrappedEmployeeDTO[],
  activated: boolean,
  creationDate: string
}
