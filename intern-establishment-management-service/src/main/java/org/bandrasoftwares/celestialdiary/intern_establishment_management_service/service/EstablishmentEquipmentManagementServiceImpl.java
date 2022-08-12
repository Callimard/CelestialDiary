package org.bandrasoftwares.celestialdiary.intern_establishment_management_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.aop.SearchingAspect;
import org.bandrsoftwares.celestialdiary.aop.company.CheckCompanyCoherence;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.equipment.EquipmentId;
import org.bandrsoftwares.celestialdiary.aop.equipment.SearchEquipment;
import org.bandrsoftwares.celestialdiary.aop.establishment.EstablishmentId;
import org.bandrsoftwares.celestialdiary.aop.establishment.SearchEstablishment;
import org.bandrsoftwares.celestialdiary.model.mongodb.equipment.Equipment;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.EstablishmentEquipment;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Validated
@Service
public class EstablishmentEquipmentManagementServiceImpl implements EstablishmentEquipmentManagementService {

    // Methods.

    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public List<EstablishmentEquipment> allEstablishmentEquipments(@CompanyId String companyId, @EstablishmentId String establishmentId) {
        return SearchingAspect.ESTABLISHMENT_FOUND.get().getEquipments().stream().toList();
    }

    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public List<EstablishmentEquipment> searchEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                                                     String filter) {
        String regexFilter = "(?i).*" + filter + ".*";
        return SearchingAspect.ESTABLISHMENT_FOUND.get().getEquipments().stream()
                .filter(establishmentEquipment -> establishmentEquipment.getEquipment().getName().matches(regexFilter)).toList();
    }

    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public EstablishmentEquipment getSpecificEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                                                    @EquipmentId String equipmentId) {
        List<EstablishmentEquipment> equipments = SearchingAspect.ESTABLISHMENT_FOUND.get().getEquipments().stream()
                .filter(establishmentEquipment -> establishmentEquipment.getEquipment().getId().equals(equipmentId)).toList();
        if (!equipments.isEmpty()) {
            return equipments.get(0);
        } else {
            return null;
        }
    }

    @SearchEstablishment
    @SearchEquipment
    @CheckCompanyCoherence
    @Override
    public EstablishmentEquipment addEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                                            @EquipmentId String equipmentId, @Valid @Min(1) int quantity) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();
        Equipment equipment = SearchingAspect.EQUIPMENT_FOUND.get();

        EstablishmentEquipment establishmentEquipment = createEstablishmentEquipmentFrom(quantity, equipment);

        boolean added = establishment.getEquipments().add(establishmentEquipment);
        if (added) {
            return establishmentEquipment;
        } else {
            return null;
        }
    }

    private EstablishmentEquipment createEstablishmentEquipmentFrom(int quantity, Equipment equipment) {
        return EstablishmentEquipment.builder()
                .quantity(quantity)
                .numberAvailable(quantity)
                .equipment(equipment)
                .build();
    }

    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public EstablishmentEquipment updateEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                                               @EquipmentId String equipmentId,
                                                               @Valid EstablishmentEquipmentUpdatedInformation updates) {
        EstablishmentEquipment establishmentEquipment = getSpecificEstablishmentEquipment(companyId, establishmentId, equipmentId);
        if (establishmentEquipment != null) {
            establishmentEquipment.setQuantity(updates.quantity());
            establishmentEquipment.setNumberAvailable(updates.numberAvailable());
            return establishmentEquipment;
        } else {
            return null;
        }
    }

    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public boolean deleteEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                                @EquipmentId String equipmentId) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();
        return establishment.getEquipments().removeIf(establishmentEquipment -> establishmentEquipment.getEquipment().getId().equals(equipmentId));
    }
}
