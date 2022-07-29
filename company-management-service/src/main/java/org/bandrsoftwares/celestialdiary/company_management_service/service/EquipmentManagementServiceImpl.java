package org.bandrsoftwares.celestialdiary.company_management_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.aop.SearchingAspect;
import org.bandrsoftwares.celestialdiary.aop.company.CheckCompanyCoherence;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.company.SearchCompany;
import org.bandrsoftwares.celestialdiary.aop.equipment.EquipmentId;
import org.bandrsoftwares.celestialdiary.aop.equipment.SearchEquipment;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.equipment.Equipment;
import org.bandrsoftwares.celestialdiary.model.mongodb.equipment.EquipmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Validated
@Service
public class EquipmentManagementServiceImpl implements EquipmentManagementService {

    // Variables.

    private final EquipmentRepository equipmentRepository;

    // Methods.

    @SearchCompany
    @Override
    public List<Equipment> allRegisteredEquipment(@CompanyId String companyId) {
        return equipmentRepository.findByCompany(SearchingAspect.COMPANY_FOUND.get());
    }

    @SearchCompany
    @Override
    public List<Equipment> searchEquipment(@CompanyId String companyId, String filter) {
        String regexFilter = ".*" + filter + ".*";
        return equipmentRepository.findByCompanyAndNameRegex(SearchingAspect.COMPANY_FOUND.get(), regexFilter);
    }

    @SearchEquipment
    @CheckCompanyCoherence
    @Override
    public Equipment getSpecificEquipment(@CompanyId String companyId, @EquipmentId String equipmentId) {
        return SearchingAspect.EQUIPMENT_FOUND.get();
    }

    @SearchCompany
    @Override
    public Equipment createEquipment(@CompanyId String companyId, @Valid EquipmentCreationInformation equipmentCreationInformation) {
        return equipmentRepository.insert(createEquipmentFrom(SearchingAspect.COMPANY_FOUND.get(), equipmentCreationInformation));
    }

    private Equipment createEquipmentFrom(Company company, EquipmentCreationInformation info) {
        return Equipment.builder()
                .name(info.name())
                .description(info.description())
                .photo(info.photo())
                .company(company)
                .creationDate(Instant.now())
                .build();
    }

    @SearchEquipment
    @CheckCompanyCoherence
    @Override
    public Equipment updateEquipmentInformation(@CompanyId String companyId, @EquipmentId String equipmentId,
                                                EquipmentUpdatedInformation updates) {

        Equipment equipment = SearchingAspect.EQUIPMENT_FOUND.get();

        if (updates.name() != null && !updates.name().isBlank()) {
            equipment.setName(updates.name());
        }

        // Description can be null
        equipment.setDescription(updates.description());

        // Photo can be null
        equipment.setPhoto(updates.photo());

        return equipmentRepository.save(equipment);
    }

    @SearchEquipment
    @CheckCompanyCoherence
    @Override
    public void deleteEquipment(@CompanyId String companyId, @EquipmentId String equipmentId) {
        equipmentRepository.delete(SearchingAspect.EQUIPMENT_FOUND.get());
    }
}
