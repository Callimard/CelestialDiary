package org.bandrsoftwares.celestialdiary.company_management_service.service;

import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.equipment.EquipmentId;
import org.bandrsoftwares.celestialdiary.model.mongodb.equipment.Equipment;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface EquipmentManagementService {

    List<Equipment> allRegisteredEquipment(@CompanyId String companyId);

    List<Equipment> searchEquipment(@CompanyId String companyId, String filter);

    Equipment getSpecificEquipment(@CompanyId String companyId, @EquipmentId String equipmentId);

    Equipment createEquipment(@CompanyId String companyId, @Valid EquipmentCreationInformation equipmentCreationInformation);

    record EquipmentCreationInformation(@NotNull @NotBlank String name, String description, String photo) {
    }

    Equipment updateEquipmentInformation(@CompanyId String companyId, @EquipmentId String equipmentId,
                                         EquipmentUpdatedInformation equipmentUpdatedInformation);

    record EquipmentUpdatedInformation(String name, String description, String photo) {
    }

    void deleteEquipment(@CompanyId String companyId, @EquipmentId String equipmentId);
}
