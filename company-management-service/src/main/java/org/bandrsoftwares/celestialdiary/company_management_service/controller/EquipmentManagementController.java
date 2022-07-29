package org.bandrsoftwares.celestialdiary.company_management_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.company_management_service.service.EquipmentManagementService;
import org.bandrsoftwares.celestialdiary.model.dto.equipment.EquipmentDTO;
import org.bandrsoftwares.celestialdiary.security.privilege.company.equipment.CreateEquipmentPrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.company.equipment.DeleteEquipmentPrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.company.equipment.ReadEquipmentPrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.company.equipment.UpdateEquipmentPrivilege;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.bandrsoftwares.celestialdiary.api.v1.ApiCompanyV1.EQUIPMENT_URL;
import static org.bandrsoftwares.celestialdiary.api.v1.ApiCompanyV1.SPECIFIC_EQUIPMENT;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(EQUIPMENT_URL)
public class EquipmentManagementController {

    // Variables.

    private final EquipmentManagementService equipmentManagementService;

    // Methods.

    @ReadEquipmentPrivilege
    @GetMapping
    public List<EquipmentDTO> getEquipments(@PathVariable(name = "idCompany") String idCompany,
                                            @RequestParam(name = "filter", required = false) String filter) {
        if (filter == null) {
            return equipmentManagementService.allRegisteredEquipment(idCompany).stream().map(EquipmentDTO::new).toList();
        } else {
            return equipmentManagementService.searchEquipment(idCompany, filter).stream().map(EquipmentDTO::new).toList();
        }
    }

    @CreateEquipmentPrivilege
    @PostMapping
    public EquipmentDTO createEquipment(@PathVariable(name = "idCompany") String idCompany,
                                        @RequestBody EquipmentManagementService.EquipmentCreationInformation information) {
        return new EquipmentDTO(equipmentManagementService.createEquipment(idCompany, information));
    }

    @ReadEquipmentPrivilege
    @GetMapping(SPECIFIC_EQUIPMENT)
    public EquipmentDTO getSpecificEquipment(@PathVariable(name = "idCompany") String idCompany,
                                             @PathVariable(name = "idEquipment") String idEquipment) {
        return new EquipmentDTO(equipmentManagementService.getSpecificEquipment(idCompany, idEquipment));
    }

    @UpdateEquipmentPrivilege
    @PutMapping(SPECIFIC_EQUIPMENT)
    public EquipmentDTO updateEquipment(@PathVariable(name = "idCompany") String idCompany,
                                        @PathVariable(name = "idEquipment") String idEquipment,
                                        @RequestBody EquipmentManagementService.EquipmentUpdatedInformation updates) {
        return new EquipmentDTO(equipmentManagementService.updateEquipmentInformation(idCompany, idEquipment, updates));
    }

    @DeleteEquipmentPrivilege
    @DeleteMapping(SPECIFIC_EQUIPMENT)
    public void deleteRole(@PathVariable(name = "idCompany") String idCompany,
                           @PathVariable(name = "idEquipment") String idEquipment) {
        equipmentManagementService.deleteEquipment(idCompany, idEquipment);
    }
}
