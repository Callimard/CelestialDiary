package org.bandrasoftwares.celestialdiary.intern_establishment_management_service.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
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
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.EstablishmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Validated
@Service
public class EstablishmentEquipmentManagementServiceImpl implements EstablishmentEquipmentManagementService {

    // Variables.

    private final EstablishmentRepository establishmentRepository;

    // Methods.

    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public List<EstablishmentEquipment> allEstablishmentEquipments(@CompanyId String companyId, @EstablishmentId String establishmentId) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();

        if (establishment.getEquipments() != null) {
            return establishment.getEquipments().stream().toList();
        } else {
            return Lists.newArrayList();
        }
    }

    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public List<EstablishmentEquipment> searchEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                                                     String filter) {
        String regexFilter = "(?i).*" + filter + ".*";
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();
        if (establishment.getEquipments() != null) {
            return establishment.getEquipments().stream()
                    .filter(establishmentEquipment -> establishmentEquipment.getEquipment().getName().matches(regexFilter)).toList();
        } else {
            return Lists.newArrayList();
        }
    }

    @SearchEstablishment
    @SearchEquipment
    @CheckCompanyCoherence
    @Override
    public EstablishmentEquipment getSpecificEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                                                    @EquipmentId String equipmentId) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();
        if (establishment.getEquipments() != null) {
            return getEstablishmentEquipment(equipmentId, establishment);
        }
        return null;

    }

    @SearchEstablishment
    @SearchEquipment
    @CheckCompanyCoherence
    @Override
    public EstablishmentEquipment addEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                                            @EquipmentId String equipmentId,
                                                            @Valid EstablishmentEquipmentAddingInformation addingInformation) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();
        Equipment equipment = SearchingAspect.EQUIPMENT_FOUND.get();

        EstablishmentEquipment establishmentEquipment = createEstablishmentEquipmentFrom(addingInformation.quantity(), equipment);

        if (establishment.getEquipments() == null) {
            establishment.setEquipments(Sets.newHashSet());
        }

        boolean added = establishment.getEquipments().add(establishmentEquipment);
        if (added) {
            establishmentRepository.save(establishment);
            return establishmentEquipment;
        } else {
            return null;
        }
    }

    private EstablishmentEquipment createEstablishmentEquipmentFrom(int quantity, Equipment equipment) {
        return EstablishmentEquipment.builder()
                .quantity(quantity)
                .numberUnusable(0)
                .equipment(equipment)
                .build();
    }

    @SearchEstablishment
    @SearchEquipment
    @CheckCompanyCoherence
    @Override
    public EstablishmentEquipment updateEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                                               @EquipmentId String equipmentId,
                                                               @Valid EstablishmentEquipmentUpdatedInformation updates) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();
        if (establishment.getEquipments() != null) {
            EstablishmentEquipment establishmentEquipment =
                    getEstablishmentEquipment(equipmentId, establishment);
            if (establishmentEquipment != null) {
                establishmentEquipment.setQuantity(updates.quantity());
                establishmentEquipment.setNumberUnusable(updates.numberUnusable());
                establishmentRepository.save(establishment);
                return establishmentEquipment;
            }
        }
        return null;
    }

    private EstablishmentEquipment getEstablishmentEquipment(String equipmentId, Establishment establishment) {
        List<EstablishmentEquipment> equipments = establishment.getEquipments().stream()
                .filter(establishmentEquipment -> establishmentEquipment.getEquipment().getId().equals(equipmentId)).toList();
        return equipments.get(0);
    }

    @SearchEstablishment
    @SearchEquipment
    @CheckCompanyCoherence
    @Override
    public boolean deleteEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                                @EquipmentId String equipmentId) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();
        if (establishment.getEquipments() != null) {
            boolean removed = establishment.getEquipments()
                    .removeIf(establishmentEquipment -> establishmentEquipment.getEquipment().getId().equals(equipmentId));

            if (removed) {
                establishmentRepository.save(establishment);
            }

            return removed;
        } else
            return false;
    }
}
