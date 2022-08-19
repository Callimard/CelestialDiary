package org.bandrasoftwares.celestialdiary.intern_establishment_management_service.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.NonNull;
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
import org.bandrsoftwares.celestialdiary.model.mongodb.equipment.EquipmentRepository;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.EstablishmentEquipment;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.EstablishmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Validated
@Service
public class EstablishmentEquipmentManagementServiceImpl implements EstablishmentEquipmentManagementService {

    // Variables.

    private final EstablishmentRepository establishmentRepository;
    private final EquipmentRepository equipmentRepository;

    // Methods.

    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public Map<Equipment, List<EstablishmentEquipment>> allEstablishmentEquipments(@CompanyId String companyId,
                                                                                   @EstablishmentId String establishmentId) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();

        if (establishment.getEquipments() != null) {
            Map<Equipment, List<EstablishmentEquipment>> res = Maps.newHashMap();
            for (String equipmentId : establishment.getEquipments().keySet()) {
                Optional<Equipment> opEquipment = equipmentRepository.findById(equipmentId);
                opEquipment.ifPresent(equipment -> res.put(equipment, establishment.getEquipments().get(equipmentId).values().stream().toList()));
            }

            return res;
        } else {
            return Maps.newHashMap();
        }
    }

    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public Map<Equipment, List<EstablishmentEquipment>> searchEstablishmentEquipment(@CompanyId String companyId,
                                                                                     @EstablishmentId String establishmentId,
                                                                                     String filter) {
        String regexFilter = "(?i).*" + filter + ".*";
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();

        if (establishment.getEquipments() != null) {
            Map<Equipment, List<EstablishmentEquipment>> res = Maps.newHashMap();
            Stream<EstablishmentEquipment> searchStream =
                    establishment.getEquipments().values().stream().map(Map::values).flatMap(Collection::stream);
            List<EstablishmentEquipment> filteredEquipments =
                    searchStream.filter(establishmentEquipment -> establishmentEquipment.getName().matches(regexFilter)).toList();

            Map<String, Equipment> mapEquipmentIds = Maps.newHashMap();
            for (EstablishmentEquipment establishmentEquipment : filteredEquipments) {
                Equipment equipment = mapEquipmentIds.computeIfAbsent(establishmentEquipment.getEquipmentId(),
                                                                      k -> equipmentRepository.findById(establishmentEquipment.getEquipmentId())
                                                                              .orElse(null));
                if (equipment != null) {
                    res.computeIfAbsent(equipment, k -> Lists.newArrayList()).add(establishmentEquipment);
                }
            }

            return res;
        } else {
            return Maps.newHashMap();
        }
    }

    @SearchEstablishment
    @SearchEquipment
    @CheckCompanyCoherence
    @Override
    public EstablishmentEquipment getSpecificEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                                                    @EquipmentId String equipmentId, @NonNull String establishmentEquipmentId) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();
        return establishment.getEstablishmentEquipment(equipmentId, establishmentEquipmentId);

    }

    @SearchEstablishment
    @SearchEquipment
    @CheckCompanyCoherence
    @Override
    public Establishment addEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                                   @EquipmentId String equipmentId,
                                                   @Valid EstablishmentEquipmentAddingInformation addingInformation) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();
        Equipment equipment = SearchingAspect.EQUIPMENT_FOUND.get();

        if (establishment.getEquipments() == null) {
            establishment.setEquipments(Maps.newHashMap());
        }

        for (int i = 0; i < addingInformation.quantity(); i++) {
            EstablishmentEquipment establishmentEquipment = createEstablishmentEquipmentFor(equipment, i);
            establishment.getEquipments().computeIfAbsent(equipment.getId(), k -> Maps.newHashMap()).put(establishmentEquipment.getId(),
                                                                                                         establishmentEquipment);
        }

        return establishmentRepository.save(establishment);
    }

    private EstablishmentEquipment createEstablishmentEquipmentFor(Equipment equipment, int i) {
        return EstablishmentEquipment.builder()
                .id(UUID.randomUUID().toString().replace("-", ""))
                .equipmentId(equipment.getId())
                .name(equipment.getName() + " " + i)
                .available(true)
                .build();
    }

    @SearchEstablishment
    @SearchEquipment
    @CheckCompanyCoherence
    @Override
    public EstablishmentEquipment updateEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                                               @EquipmentId String equipmentId, @NonNull String establishmentEquipmentId,
                                                               @Valid EstablishmentEquipmentUpdatedInformation updates) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();
        if (establishment.getEquipments() != null) {
            EstablishmentEquipment establishmentEquipment = establishment.getEstablishmentEquipment(equipmentId, establishmentEquipmentId);
            if (establishmentEquipment != null) {
                establishmentEquipment.setName(updates.name());
                establishmentEquipment.setAvailable(updates.available());
                establishmentEquipment.setPhoto(updates.photo());
                establishmentRepository.save(establishment);
                return establishmentEquipment;
            }
        }

        return null;
    }

    @SearchEstablishment
    @SearchEquipment
    @CheckCompanyCoherence
    @Override
    public boolean deleteEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                                @EquipmentId String equipmentId, @NonNull String establishmentEquipmentId) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();
        if (establishment.deleteEstablishmentEquipment(equipmentId, establishmentEquipmentId)) {
            establishmentRepository.save(establishment);
            return true;
        } else
            return false;
    }
}
