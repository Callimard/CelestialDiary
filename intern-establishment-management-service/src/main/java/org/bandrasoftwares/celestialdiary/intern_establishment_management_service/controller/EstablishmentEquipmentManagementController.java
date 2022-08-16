package org.bandrasoftwares.celestialdiary.intern_establishment_management_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrasoftwares.celestialdiary.intern_establishment_management_service.service.EstablishmentEquipmentManagementService;
import org.bandrsoftwares.celestialdiary.model.dto.establishment.EstablishmentEquipmentDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.EstablishmentEquipment;
import org.bandrsoftwares.celestialdiary.security.privilege.establishment.equipment.AddEstablishmentEquipmentPrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.establishment.equipment.DeleteEstablishmentEquipmentPrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.establishment.equipment.ReadEstablishmentEquipmentPrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.establishment.equipment.UpdateEstablishmentEquipmentPrivilege;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<EstablishmentEquipmentDTO> getEstablishmentEquipments(@PathVariable(name = "idCompany") String idCompany,
                                                                      @PathVariable(name = "idEstablishment") String idEstablishment,
                                                                      @RequestParam(name = "filter", required = false) String filter) {
        if (filter == null) {
            return establishmentEquipmentManagementService.allEstablishmentEquipments(idCompany, idEstablishment).stream()
                    .map(EstablishmentEquipmentDTO::new).toList();
        } else {
            return establishmentEquipmentManagementService.searchEstablishmentEquipment(idCompany, idEstablishment, filter).stream()
                    .map(EstablishmentEquipmentDTO::new).toList();
        }
    }

    @ReadEstablishmentEquipmentPrivilege
    @GetMapping(SPECIFIC_ESTABLISHMENT_EQUIPMENT)
    public EstablishmentEquipmentDTO getSpecificEstablishmentEquipment(@PathVariable(name = "idCompany") String idCompany,
                                                                       @PathVariable(name = "idEstablishment") String idEstablishment,
                                                                       @PathVariable(name = "idEquipment") String idEquipment) {
        EstablishmentEquipment establishmentEquipment = establishmentEquipmentManagementService.getSpecificEstablishmentEquipment(idCompany,
                                                                                                                                  idEstablishment,
                                                                                                                                  idEquipment);
        if (establishmentEquipment != null) {
            return new EstablishmentEquipmentDTO(establishmentEquipment);
        } else {
            return null;
        }
    }

    @AddEstablishmentEquipmentPrivilege
    @PostMapping(SPECIFIC_ESTABLISHMENT_EQUIPMENT)
    public EstablishmentEquipmentDTO addEstablishmentEquipment(@PathVariable(name = "idCompany") String idCompany,
                                                               @PathVariable(name = "idEstablishment") String idEstablishment,
                                                               @PathVariable(name = "idEquipment") String idEquipment,
                                                               @RequestBody EstablishmentEquipmentManagementService.EstablishmentEquipmentAddingInformation addingInformation) {
        return new EstablishmentEquipmentDTO(establishmentEquipmentManagementService.addEstablishmentEquipment(idCompany, idEstablishment,
                                                                                                               idEquipment, addingInformation));
    }

    @UpdateEstablishmentEquipmentPrivilege
    @PutMapping(SPECIFIC_ESTABLISHMENT_EQUIPMENT)
    public EstablishmentEquipmentDTO updateEstablishmentEquipment(@PathVariable(name = "idCompany") String idCompany,
                                                                  @PathVariable(name = "idEstablishment") String idEstablishment,
                                                                  @PathVariable(name = "idEquipment") String idEquipment,
                                                                  @RequestBody EstablishmentEquipmentManagementService.EstablishmentEquipmentUpdatedInformation updates) {
        return new EstablishmentEquipmentDTO(establishmentEquipmentManagementService.updateEstablishmentEquipment(idCompany, idEstablishment,
                                                                                                                  idEquipment, updates));
    }

    @DeleteEstablishmentEquipmentPrivilege
    @DeleteMapping(SPECIFIC_ESTABLISHMENT_EQUIPMENT)
    public boolean deleteEstablishmentEquipment(@PathVariable(name = "idCompany") String idCompany,
                                                @PathVariable(name = "idEstablishment") String idEstablishment,
                                                @PathVariable(name = "idEquipment") String idEquipment) {
        return establishmentEquipmentManagementService.deleteEstablishmentEquipment(idCompany, idEstablishment, idEquipment);
    }
}
