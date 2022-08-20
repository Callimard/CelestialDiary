package org.bandrasoftwares.celestialdiary.intern_establishment_management_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrasoftwares.celestialdiary.intern_establishment_management_service.service.EstablishmentEquipmentManagementService;
import org.bandrsoftwares.celestialdiary.model.dto.equipment.EquipmentDTO;
import org.bandrsoftwares.celestialdiary.model.dto.establishment.AdvancedEstablishmentEquipmentContainerDTO;
import org.bandrsoftwares.celestialdiary.model.dto.establishment.EstablishmentDTO;
import org.bandrsoftwares.celestialdiary.model.dto.establishment.EstablishmentEquipmentDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.equipment.Equipment;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.EstablishmentEquipment;
import org.bandrsoftwares.celestialdiary.security.privilege.establishment.equipment.AddEstablishmentEquipmentPrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.establishment.equipment.DeleteEstablishmentEquipmentPrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.establishment.equipment.ReadEstablishmentEquipmentPrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.establishment.equipment.UpdateEstablishmentEquipmentPrivilege;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.bandrsoftwares.celestialdiary.api.v1.ApiInternEstablishmentV1.ESTABLISHMENT_EQUIPMENTS_URL;
import static org.bandrsoftwares.celestialdiary.api.v1.ApiInternEstablishmentV1.SPECIFIC_ESTABLISHMENT_EQUIPMENT;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(ESTABLISHMENT_EQUIPMENTS_URL)
public class EstablishmentEquipmentManagementController {

    // Variables.

    private final EstablishmentEquipmentManagementService establishmentEquipmentManagementService;

    // Methods.

    @ReadEstablishmentEquipmentPrivilege
    @GetMapping
    public List<AdvancedEstablishmentEquipmentContainerDTO> getEstablishmentEquipments(@PathVariable(name = "idCompany") String idCompany,
                                                                                       @PathVariable(name = "idEstablishment") String idEstablishment,
                                                                                       @RequestParam(name = "filter", required = false) String filter) {
        if (filter == null) {
            return establishmentEquipmentManagementService.allEstablishmentEquipments(idCompany, idEstablishment)
                    .entrySet().stream().map(transformToAdvancedEstablishmentEquipmentContainerDTO()).toList();
        } else {
            return establishmentEquipmentManagementService.searchEstablishmentEquipment(idCompany, idEstablishment, filter)
                    .entrySet().stream().map(transformToAdvancedEstablishmentEquipmentContainerDTO()).toList();
        }
    }

    private Function<Map.Entry<Equipment, List<EstablishmentEquipment>>, AdvancedEstablishmentEquipmentContainerDTO> transformToAdvancedEstablishmentEquipmentContainerDTO() {
        return entry -> {
            Equipment equipment = entry.getKey();
            List<EstablishmentEquipment> establishmentEquipments = entry.getValue();
            return new AdvancedEstablishmentEquipmentContainerDTO(new EquipmentDTO(equipment),
                                                                  establishmentEquipments.stream().map(EstablishmentEquipmentDTO::new)
                                                                          .toList());
        };
    }

    @ReadEstablishmentEquipmentPrivilege
    @GetMapping(SPECIFIC_ESTABLISHMENT_EQUIPMENT)
    public EstablishmentEquipmentDTO getSpecificEstablishmentEquipment(@PathVariable(name = "idCompany") String idCompany,
                                                                       @PathVariable(name = "idEstablishment") String idEstablishment,
                                                                       @PathVariable(name = "idEquipment") String idEquipment,
                                                                       @RequestParam(name = "idEstablishmentEquipment") String idEstablishmentEquipment) {
        EstablishmentEquipment establishmentEquipment =
                establishmentEquipmentManagementService.getSpecificEstablishmentEquipment(idCompany, idEstablishment, idEquipment,
                                                                                          idEstablishmentEquipment);
        if (establishmentEquipment != null) {
            return new EstablishmentEquipmentDTO(establishmentEquipment);
        } else {
            return null;
        }
    }

    @AddEstablishmentEquipmentPrivilege
    @PostMapping(SPECIFIC_ESTABLISHMENT_EQUIPMENT)
    public EstablishmentDTO addEstablishmentEquipment(@PathVariable(name = "idCompany") String idCompany,
                                                      @PathVariable(name = "idEstablishment") String idEstablishment,
                                                      @PathVariable(name = "idEquipment") String idEquipment,
                                                      @RequestBody EstablishmentEquipmentManagementService.EstablishmentEquipmentAddingInformation addingInformation) {
        return new EstablishmentDTO(
                establishmentEquipmentManagementService.addEstablishmentEquipment(idCompany, idEstablishment, idEquipment, addingInformation));
    }

    @UpdateEstablishmentEquipmentPrivilege
    @PutMapping(SPECIFIC_ESTABLISHMENT_EQUIPMENT)
    public EstablishmentEquipmentDTO updateEstablishmentEquipment(@PathVariable(name = "idCompany") String idCompany,
                                                                  @PathVariable(name = "idEstablishment") String idEstablishment,
                                                                  @PathVariable(name = "idEquipment") String idEquipment,
                                                                  @RequestParam(name = "idEstablishmentEquipment") String idEstablishmentEquipment,
                                                                  @RequestBody EstablishmentEquipmentManagementService.EstablishmentEquipmentUpdatedInformation updates) {
        return new EstablishmentEquipmentDTO(establishmentEquipmentManagementService.updateEstablishmentEquipment(idCompany, idEstablishment,
                                                                                                                  idEquipment,
                                                                                                                  idEstablishmentEquipment,
                                                                                                                  updates));
    }

    @DeleteEstablishmentEquipmentPrivilege
    @DeleteMapping(SPECIFIC_ESTABLISHMENT_EQUIPMENT)
    public void deleteEstablishmentEquipment(@PathVariable(name = "idCompany") String idCompany,
                                             @PathVariable(name = "idEstablishment") String idEstablishment,
                                             @PathVariable(name = "idEquipment") String idEquipment,
                                             @RequestParam(name = "idEstablishmentEquipment") String idEstablishmentEquipment) {
        establishmentEquipmentManagementService.deleteEstablishmentEquipment(idCompany, idEstablishment, idEquipment,
                                                                             idEstablishmentEquipment);
    }
}
