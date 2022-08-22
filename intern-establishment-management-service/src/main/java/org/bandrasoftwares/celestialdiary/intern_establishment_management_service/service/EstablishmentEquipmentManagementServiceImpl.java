package org.bandrasoftwares.celestialdiary.intern_establishment_management_service.service;

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
import java.util.List;

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
    public List<EstablishmentEquipment> allEstablishmentEquipments(@CompanyId String companyId,
                                                                   @EstablishmentId String establishmentId) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();
        return establishment.allEstablishmentEquipments();
    }

    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public List<EstablishmentEquipment> searchEstablishmentEquipment(@CompanyId String companyId,
                                                                     @EstablishmentId String establishmentId,
                                                                     String filter) {
        String regexFilter = "(?i).*" + filter + ".*";
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();
        return establishment.searchEstablishmentEquipments(regexFilter);
    }

    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public EstablishmentEquipment getSpecificEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                                                    @NonNull String establishmentEquipmentId) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();

        EstablishmentEquipment establishmentEquipment = establishment.getEstablishmentEquipment(establishmentEquipmentId);
        if (establishmentEquipment != null) {
            return establishmentEquipment;
        } else {
            throw new EstablishmentEquipmentNotFoundException(establishmentEquipmentId, establishment.getName());
        }

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

        establishment.addEstablishmentEquipment(equipment, addingInformation.quantity());

        return establishmentRepository.save(establishment);
    }

    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public EstablishmentEquipment updateEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                                               @NonNull String establishmentEquipmentId,
                                                               @Valid EstablishmentEquipmentUpdatedInformation updates) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();
        EstablishmentEquipment establishmentEquipment = establishment.getEstablishmentEquipment(establishmentEquipmentId);
        if (establishmentEquipment != null) {
            establishmentEquipment.setName(updates.name());
            establishmentEquipment.setAvailable(updates.available());
            establishmentEquipment.setPhoto(updates.photo());
            establishmentRepository.save(establishment);
            return establishmentEquipment;
        } else {
            throw new EstablishmentEquipmentNotFoundException(establishmentEquipmentId, establishment.getName());
        }
    }

    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public void deleteEstablishmentEquipment(@CompanyId String companyId, @EstablishmentId String establishmentId,
                                             @NonNull String establishmentEquipmentId) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();
        if (establishment.deleteEstablishmentEquipment(establishmentEquipmentId)) {
            establishmentRepository.save(establishment);
        } else {
            throw new EstablishmentEquipmentNotFoundException(establishmentEquipmentId, establishment.getName());
        }
    }
}
