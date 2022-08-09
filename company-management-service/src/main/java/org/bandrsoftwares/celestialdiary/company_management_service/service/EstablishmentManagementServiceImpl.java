package org.bandrsoftwares.celestialdiary.company_management_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.aop.SearchingAspect;
import org.bandrsoftwares.celestialdiary.aop.company.CheckCompanyCoherence;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.company.SearchCompany;
import org.bandrsoftwares.celestialdiary.aop.establishment.EstablishmentId;
import org.bandrsoftwares.celestialdiary.aop.establishment.SearchEstablishment;
import org.bandrsoftwares.celestialdiary.model.dto.general.time.DatedTimeIntervalListDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.EstablishmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Validated
@Service
public class EstablishmentManagementServiceImpl implements EstablishmentManagementService {

    // Variables.

    private final EstablishmentRepository establishmentRepository;

    // Methods.

    @SearchCompany
    @Override
    public List<Establishment> allRegisteredEstablishment(@CompanyId String companyId) {
        return establishmentRepository.findByCompany(SearchingAspect.COMPANY_FOUND.get());
    }

    @SearchCompany
    @Override
    public List<Establishment> searchEstablishment(@CompanyId String companyId, String filter) {
        String regexFilter = ".*" + filter + ".*";
        return establishmentRepository.findByCompanyAndNameRegex(SearchingAspect.COMPANY_FOUND.get(), regexFilter);
    }

    @SearchCompany
    @Override
    public List<Establishment> searchEstablishment(@CompanyId String companyId, String[] ids) {
        return establishmentRepository.findByCompanyAndIdIn(SearchingAspect.COMPANY_FOUND.get(), Arrays.asList(ids));
    }

    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public Establishment getSpecificEstablishment(@CompanyId String companyId, @EstablishmentId String establishmentId) {
        return SearchingAspect.ESTABLISHMENT_FOUND.get();
    }

    @SearchCompany
    @Override
    public Establishment createEstablishment(@CompanyId String companyId, @Valid EstablishmentCreationInformation establishmentCreationInformation) {
        Establishment establishment = createEstablishmentFrom(SearchingAspect.COMPANY_FOUND.get(), establishmentCreationInformation);
        return establishmentRepository.insert(establishment);
    }

    private Establishment createEstablishmentFrom(Company company, @Valid EstablishmentCreationInformation info) {
        return Establishment.builder()
                .name(info.name())
                .description(info.description())
                .address(info.address())
                .mondayOpening(info.mondayOpening() != null ? info.mondayOpening().toNonDatedTimeIntervalList() : null)
                .tuesdayOpening(info.tuesdayOpening() != null ? info.tuesdayOpening().toNonDatedTimeIntervalList() : null)
                .wednesdayOpening(info.wednesdayOpening() != null ? info.wednesdayOpening().toNonDatedTimeIntervalList() : null)
                .thursdayOpening(info.thursdayOpening() != null ? info.thursdayOpening().toNonDatedTimeIntervalList() : null)
                .fridayOpening(info.fridayOpening() != null ? info.fridayOpening().toNonDatedTimeIntervalList() : null)
                .saturdayOpening(info.saturdayOpening() != null ? info.saturdayOpening().toNonDatedTimeIntervalList() : null)
                .sundayOpening(info.sundayOpening() != null ? info.sundayOpening().toNonDatedTimeIntervalList() : null)
                .activated(true)
                .company(company)
                .creationDate(Instant.now())
                .build();
    }

    @SearchCompany
    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public Establishment updateEstablishment(@CompanyId String companyId,
                                             @EstablishmentId String establishmentId,
                                             @Valid EstablishmentUpdatedInformation update) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();

        if (update.name() != null && !update.name().isBlank()) {
            establishment.setName(update.name());
        }

        if (update.description() != null) {
            establishment.setDescription(update.description());
        }

        if (update.address() != null) {
            establishment.setAddress(update.address());
        }

        if (update.mondayOpening() != null) {
            establishment.setMondayOpening(update.mondayOpening().toNonDatedTimeIntervalList());
        } else {
            establishment.setMondayOpening(null);
        }

        if (update.tuesdayOpening() != null) {
            establishment.setTuesdayOpening(update.tuesdayOpening().toNonDatedTimeIntervalList());
        } else {
            establishment.setTuesdayOpening(null);
        }

        if (update.wednesdayOpening() != null) {
            establishment.setWednesdayOpening(update.wednesdayOpening().toNonDatedTimeIntervalList());
        } else {
            establishment.setWednesdayOpening(null);
        }

        if (update.thursdayOpening() != null) {
            establishment.setThursdayOpening(update.thursdayOpening().toNonDatedTimeIntervalList());
        } else {
            establishment.setThursdayOpening(null);
        }

        if (update.fridayOpening() != null) {
            establishment.setFridayOpening(update.fridayOpening().toNonDatedTimeIntervalList());
        } else {
            establishment.setFridayOpening(null);
        }

        if (update.saturdayOpening() != null) {
            establishment.setSaturdayOpening(update.saturdayOpening().toNonDatedTimeIntervalList());
        } else {
            establishment.setSaturdayOpening(null);
        }

        if (update.sundayOpening() != null) {
            establishment.setSundayOpening(update.sundayOpening().toNonDatedTimeIntervalList());
        } else {
            establishment.setSundayOpening(null);
        }

        if (update.exceptionalOpening() != null) {
            establishment.setExceptionalOpening(update.exceptionalOpening().stream().map(DatedTimeIntervalListDTO::toDatedTimeIntervalList).toList());
        }

        if (update.exceptionalClosing() != null) {
            establishment.setExceptionalClosing(update.exceptionalClosing().stream().map(DatedTimeIntervalListDTO::toDatedTimeIntervalList).toList());
        }

        return establishmentRepository.save(establishment);
    }

    @SearchCompany
    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public boolean activateEstablishment(@CompanyId String companyId, @EstablishmentId String establishmentId) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();

        if (Boolean.FALSE.equals(establishment.getActivated())) {
            establishment.setActivated(true);
            establishmentRepository.save(establishment);
            return true;
        }
        return false;
    }

    @SearchCompany
    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public boolean deactivateEstablishment(@CompanyId String companyId, @EstablishmentId String establishmentId) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();

        if (Boolean.TRUE.equals(establishment.getActivated())) {
            establishment.setActivated(false);
            establishmentRepository.save(establishment);
            return true;
        }
        return false;
    }
}
