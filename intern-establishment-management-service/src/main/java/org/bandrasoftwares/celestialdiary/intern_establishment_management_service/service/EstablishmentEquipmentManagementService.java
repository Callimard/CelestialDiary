package org.bandrasoftwares.celestialdiary.intern_establishment_management_service.service;

import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.equipment.EquipmentId;
import org.bandrsoftwares.celestialdiary.aop.establishment.EstablishmentId;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.EstablishmentEquipment;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

public interface EstablishmentEquipmentManagementService {

    List<EstablishmentEquipment> allEstablishmentEquipments(@CompanyId String companyId, @EstablishmentId String establishmentId);

    List<EstablishmentEquipment> searchEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId, String filter);

    EstablishmentEquipment getSpecificEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                                             @EquipmentId String equipmentId);

    EstablishmentEquipment addEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                                     @EquipmentId String equipmentId,
                                                     @Valid EstablishmentEquipmentAddingInformation addingInformation);

    record EstablishmentEquipmentAddingInformation(@Min(1) int quantity) {
    }

    EstablishmentEquipment updateEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                                        @EquipmentId String equipmentId, @Valid EstablishmentEquipmentUpdatedInformation updates);

    record EstablishmentEquipmentUpdatedInformation(@Min(1) Integer quantity, @Min(0) Integer numberUnusable) {
    }

    boolean deleteEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                         @EquipmentId String equipmentId);
}
