package org.bandrasoftwares.celestialdiary.intern_establishment_management_service.service;

import lombok.NonNull;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.equipment.EquipmentId;
import org.bandrsoftwares.celestialdiary.aop.establishment.EstablishmentId;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.EstablishmentEquipment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

public interface EstablishmentEquipmentManagementService {

    List<EstablishmentEquipment> allEstablishmentEquipments(@CompanyId String companyId, @EstablishmentId String establishmentId);

    List<EstablishmentEquipment> searchEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                                              String filter);

    EstablishmentEquipment getSpecificEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                                             @NonNull String establishmentEquipmentId);

    Establishment addEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                            @EquipmentId String equipmentId,
                                            @Valid EstablishmentEquipmentAddingInformation addingInformation);

    record EstablishmentEquipmentAddingInformation(@Min(1) int quantity) {
    }

    EstablishmentEquipment updateEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                                        @NonNull String establishmentEquipmentId,
                                                        @Valid EstablishmentEquipmentUpdatedInformation updates);

    record EstablishmentEquipmentUpdatedInformation(@NonNull String name, boolean available, String photo) {
    }

    void deleteEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                      @NonNull String establishmentEquipmentId);

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    class EstablishmentEquipmentNotFoundException extends RuntimeException {
        public EstablishmentEquipmentNotFoundException(String establishmentEquipmentId, String establishmentName) {
            super("The establishment equipment " + establishmentEquipmentId + " has not been found in the establishment " + establishmentName);
        }
    }
}
