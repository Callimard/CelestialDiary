package org.bandrasoftwares.celestialdiary.intern_establishment_management_service.service;

import lombok.NonNull;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.equipment.EquipmentId;
import org.bandrsoftwares.celestialdiary.aop.establishment.EstablishmentId;
import org.bandrsoftwares.celestialdiary.model.mongodb.equipment.Equipment;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.EstablishmentEquipment;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

public interface EstablishmentEquipmentManagementService {

    Map<Equipment, List<EstablishmentEquipment>> allEstablishmentEquipments(@CompanyId String companyId, @EstablishmentId String establishmentId);

    Map<Equipment, List<EstablishmentEquipment>> searchEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                                                              String filter);

    EstablishmentEquipment getSpecificEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                                             @EquipmentId String equipmentId, @NonNull String establishmentEquipmentId);

    Establishment addEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                            @EquipmentId String equipmentId,
                                            @Valid EstablishmentEquipmentAddingInformation addingInformation);

    record EstablishmentEquipmentAddingInformation(@Min(1) int quantity) {
    }

    EstablishmentEquipment updateEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                               @EquipmentId String equipmentId, @NonNull String establishmentEquipmentId,
                                               @Valid EstablishmentEquipmentUpdatedInformation updates);

    record EstablishmentEquipmentUpdatedInformation(@NonNull String name, boolean available, String photo) {
    }

    boolean deleteEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                         @EquipmentId String equipmentId, @NonNull String establishmentEquipmentId);
}
