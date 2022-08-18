import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {EstablishmentEquipmentManagementService} from "../../../../../service/intern-establishment-management/equipment/establishment-equipment-management.service";
import {Observable} from "rxjs";
import {EstablishmentEquipmentDTO} from "../../../../../data/model/establishment/establishment-equipment-dto";
import {ActivatedRoute} from "@angular/router";
import {PrivilegeService} from "../../../../../service/authentication/privilege.service";
import {AdvancedEstablishmentEquipmentContainerDTO} from "../../../../../data/model/establishment/advanced-establishment-equipment-container-dto";
import {EquipmentPaneInfoTransformer} from "../../../company-management/equipment-management/equipment-selection/equipment-selection.component";

@Component({
  selector: '[app-establishment-equipment-selection]',
  templateUrl: './establishment-equipment-selection.component.html',
  styleUrls: ['./establishment-equipment-selection.component.css']
})
export class EstablishmentEquipmentSelectionComponent implements OnInit {

  @Output() establishmentEquipmentSelected = new EventEmitter<EstablishmentEquipmentDTO>();
  @Output() wantAddEstablishmentEquipment = new EventEmitter<boolean>();

  establishmentId!: string;

  equipments$!: Observable<AdvancedEstablishmentEquipmentContainerDTO[]>;

  allEquipments: AdvancedEstablishmentEquipmentContainerDTO[] = [];

  equipmentTransformer = new EquipmentPaneInfoTransformer();

  constructor(private establishmentEquipmentManagementService: EstablishmentEquipmentManagementService,
              private privilegeService: PrivilegeService,
              private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.establishmentId = params['establishmentId'];
      this.equipments$ = this.establishmentEquipmentManagementService.allEstablishmentEquipments(this.establishmentId);
      this.chargeEstablishmentEquipments();
    });
  }

  public reload() {
    this.chargeEstablishmentEquipments();
  }

  private chargeEstablishmentEquipments() {
    this.equipments$.subscribe((allEquipments) => {
      this.allEquipments = allEquipments;
      EstablishmentEquipmentSelectionComponent.sortByEquipmentName(this.allEquipments);
    });
  }

  filterEstablishmentEquipments(filter: string) {
    this.establishmentEquipmentManagementService.searchEstablishmentEquipments(this.establishmentId, filter).subscribe((allEquipments) => {
      this.allEquipments = allEquipments;
      EstablishmentEquipmentSelectionComponent.sortByEquipmentName(this.allEquipments);
    });
  }

  navigateToAddEstablishmentEquipment() {
    this.wantAddEstablishmentEquipment.emit(true);
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }

  private static sortByEquipmentName(allEquipments: AdvancedEstablishmentEquipmentContainerDTO[]) {
    allEquipments.sort((aEEquipment0, aEEquipment1) => {
      return aEEquipment0.equipment.name.localeCompare(aEEquipment1.equipment.name);
    });
    for (let eEquipment of allEquipments) {
      eEquipment.establishmentEquipments.sort((e0, e1) => {
        return e0.name.localeCompare(e1.name);
      })
    }
  }
}
