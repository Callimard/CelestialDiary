package org.bandrsoftwares.celestialdiary.company_management_service.service;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.aop.SearchingAspect;
import org.bandrsoftwares.celestialdiary.aop.company.CheckCompanyCoherence;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.company.SearchCompany;
import org.bandrsoftwares.celestialdiary.aop.saleable.service.PrestationId;
import org.bandrsoftwares.celestialdiary.aop.saleable.service.SearchPrestation;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.equipment.Equipment;
import org.bandrsoftwares.celestialdiary.model.mongodb.equipment.EquipmentRepository;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.prestation.Prestation;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.prestation.PrestationRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Validated
@Service
public class PrestationManagementServiceImpl implements PrestationManagementService {

    // Variables.

    private final PrestationRepository prestationRepository;
    private final EquipmentRepository equipmentRepository;

    // Methods.

    @SearchCompany
    @Override
    public List<Prestation> allRegisteredPrestation(@CompanyId String companyId) {
        return prestationRepository.findByCompany(SearchingAspect.COMPANY_FOUND.get());
    }

    @SearchCompany
    @Override
    public List<Prestation> searchPrestation(@CompanyId String companyId, String filter) {
        String regexFilter = ".*" + filter + ".*";
        return prestationRepository.findByCompanyAndNameRegex(SearchingAspect.COMPANY_FOUND.get(), regexFilter);
    }

    @SearchPrestation
    @CheckCompanyCoherence
    @Override
    public Prestation getSpecificPrestation(@CompanyId String companyId, @PrestationId String prestationId) {
        return SearchingAspect.PRESTATION_FOUND.get();
    }

    @SearchCompany
    @Override
    public Prestation createPrestation(@CompanyId String companyId, @Valid PrestationManagementService.PrestationCreationInformation information) {
        Prestation prestation = createPrestationFrom(SearchingAspect.COMPANY_FOUND.get(), information);
        return prestationRepository.insert(prestation);
    }

    private Prestation createPrestationFrom(Company company, PrestationCreationInformation info) {
        List<Equipment> equipments = Lists.newArrayList();
        if (info.neededEquipments() != null && !info.neededEquipments().isEmpty()) {
            equipments = equipmentRepository.findByCompanyAndIdIn(company, info.neededEquipments());
        }

        return Prestation.builder()
                .name(info.name())
                .description(info.description())
                .suggestedPrice(info.suggestedPrice())
                .nbNeededTechnician(info.nbNeededTechnician())
                .nbClient(info.nbClient())
                .suggestedExecutionTime(info.suggestedExecutionTime())
                .neededEquipments(equipments)
                .activated(true)
                .creationDate(Instant.now())
                .company(company)
                .build();
    }

    @SearchCompany
    @SearchPrestation
    @CheckCompanyCoherence
    @Override
    public Prestation updatePrestation(@CompanyId String companyId, @PrestationId String prestationId,
                                       @Valid PrestationManagementService.PrestationUpdatedInformation update) {
        Prestation prestation = SearchingAspect.PRESTATION_FOUND.get();

        if (update.name() != null && !update.name().isBlank()) {
            prestation.setName(update.name());
        }

        if (update.description() != null) {
            prestation.setDescription(update.description());
        }

        if (update.suggestedPrice() != null) {
            prestation.setSuggestedPrice(update.suggestedPrice());
        }

        if (update.nbNeededTechnician() != null && update.nbNeededTechnician() >= 1) {
            prestation.setNbNeededTechnician(update.nbNeededTechnician());
        }

        if (update.nbClient() != null && update.nbClient() >= 1) {
            prestation.setNbClient(update.nbClient());
        }

        if (update.suggestedExecutionTime() != null && update.suggestedExecutionTime() >= 1) {
            prestation.setSuggestedExecutionTime(update.suggestedExecutionTime());
        }

        if (update.neededEquipments() != null && !update.neededEquipments().isEmpty()) {
            List<Equipment> equipments = equipmentRepository.findByCompanyAndIdIn(SearchingAspect.COMPANY_FOUND.get(), update.neededEquipments());
            prestation.setNeededEquipments(equipments);
        } else {
            prestation.setNeededEquipments(Lists.newArrayList());
        }

        return prestationRepository.save(prestation);
    }

    @SearchPrestation
    @CheckCompanyCoherence
    @Override
    public boolean activatePrestation(@CompanyId String companyId, @PrestationId String prestationId) {
        Prestation prestation = SearchingAspect.PRESTATION_FOUND.get();

        if (Boolean.FALSE.equals(prestation.getActivated())) {
            prestation.setActivated(true);
            prestationRepository.save(prestation);
            return true;
        }
        return false;
    }

    @SearchPrestation
    @CheckCompanyCoherence
    @Override
    public boolean deactivatePrestation(@CompanyId String companyId, @PrestationId String prestationId) {
        Prestation prestation = SearchingAspect.PRESTATION_FOUND.get();

        if (Boolean.TRUE.equals(prestation.getActivated())) {
            prestation.setActivated(false);
            prestationRepository.save(prestation);
            return true;
        }
        return false;
    }
}
