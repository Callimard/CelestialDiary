import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {EstablishmentEquipmentManagementService} from "../../../../../service/intern-establishment-management/equipment/establishment-equipment-management.service";
import {Observable} from "rxjs";
import {EstablishmentEquipmentDTO} from "../../../../../data/model/establishment/establishment-equipment-dto";
import {PrivilegeService} from "../../../../../service/authentication/privilege.service";
import {EquipmentManagementService} from "../../../../../service/company-management/equipment/equipment-management.service";
import {EquipmentDTO} from "../../../../../data/model/equipment/equipment-dto";

@Component({
  selector: '[app-establishment-equipment-selection]',
  templateUrl: './establishment-equipment-selection.component.html',
  styleUrls: ['./establishment-equipment-selection.component.css']
})
export class EstablishmentEquipmentSelectionComponent implements OnInit, OnChanges {

  @Input() establishmentId!: string;

  @Output() establishmentEquipmentSelected = new EventEmitter<EstablishmentEquipmentDTO>();
  @Output() wantAddEstablishmentEquipment = new EventEmitter<boolean>();

  allEquipments: Map<string, EquipmentDTO> = new Map<string, EquipmentDTO>();
  allEquipmentCharged: boolean = false;

  establishmentEquipments$!: Observable<EstablishmentEquipmentDTO[]>;
  allEstablishmentEquipments: EstablishmentEquipmentDTO[] = [];
  formattedEstablishmentEquipments: { equipmentId: string, establishmentEquipments: EstablishmentEquipmentDTO[] }[] = [];

  equipmentsMap: { [equipmentId: string]: EstablishmentEquipmentDTO[] } = {};

  constructor(private establishmentEquipmentManagementService: EstablishmentEquipmentManagementService,
              private equipmentManagementService: EquipmentManagementService,
              private privilegeService: PrivilegeService) {
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    this.chargeAllEquipments();
    this.establishmentEquipments$ = this.establishmentEquipmentManagementService.allEstablishmentEquipments(this.establishmentId);
    this.chargeEstablishmentEquipments();
  }

  public reload() {
    this.chargeEstablishmentEquipments();
  }

  private chargeAllEquipments() {
    this.equipmentManagementService.allEquipments().then((allEquipments) => {
      for (let equipment of allEquipments) {
        this.allEquipments.set(equipment.id, equipment);
      }
      this.allEquipmentCharged = true;
    })
  }

  private chargeEstablishmentEquipments() {
    this.establishmentEquipments$.subscribe((allEquipments) => {
      this.allEstablishmentEquipments = allEquipments;
      this.initEquipmentsMap();
    });
  }

  filterEstablishmentEquipments(filter: string) {
    this.establishmentEquipmentManagementService.searchEstablishmentEquipments(this.establishmentId, filter).subscribe((allEquipments) => {
      this.allEstablishmentEquipments = allEquipments;
      this.initEquipmentsMap();
    });
  }

  private initEquipmentsMap() {
    this.equipmentsMap = {};
    for (let establishmentEquipment of this.allEstablishmentEquipments) {
      if (this.equipmentsMap[establishmentEquipment.equipmentId] == null) {
        this.equipmentsMap[establishmentEquipment.equipmentId] = [];
      }

      this.equipmentsMap[establishmentEquipment.equipmentId].push(establishmentEquipment);
    }
    this.initFormattedEstablishmentEquipments();
  }

  private initFormattedEstablishmentEquipments() {
    this.formattedEstablishmentEquipments = [];
    for (let key of Object.keys(this.equipmentsMap).sort((k1, k2) => k1.localeCompare(k2))) {
      this.formattedEstablishmentEquipments.push({
        equipmentId: key,
        establishmentEquipments: this.equipmentsMap[key]
      });
    }

    for (let elem of this.formattedEstablishmentEquipments) {
      elem.establishmentEquipments = [...elem.establishmentEquipments].sort((eEq1, eEq2) => eEq1.name.localeCompare(eEq2.name));
    }
  }

  get establishmentEquipments(): { equipmentId: string, establishmentEquipments: EstablishmentEquipmentDTO[] }[] {
    return this.formattedEstablishmentEquipments;
  }

  navigateToAddEstablishmentEquipment() {
    this.wantAddEstablishmentEquipment.emit(true);
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
