package org.bandrsoftwares.celestialdiary.company_management_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.aop.SearchingAspect;
import org.bandrsoftwares.celestialdiary.aop.company.CheckCompanyCoherence;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.company.SearchCompany;
import org.bandrsoftwares.celestialdiary.aop.establishment.EstablishmentId;
import org.bandrsoftwares.celestialdiary.aop.establishment.SearchEstablishment;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.EstablishmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
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
    public Establishment createEstablishment(@CompanyId String companyId, @Valid EstablishmentCreationInformation establishmentCreationInformation) {
        Establishment establishment = createEstablishmentFrom(SearchingAspect.COMPANY_FOUND.get(), establishmentCreationInformation);
        return establishmentRepository.insert(establishment);
    }

    private Establishment createEstablishmentFrom(Company company, @Valid EstablishmentCreationInformation info) {
        return Establishment.builder()
                .name(info.name())
                .description(info.description())
                .address(info.address())
                .mondayOpening(info.mondayOpening())
                .tuesdayOpening(info.tuesdayOpening())
                .wednesdayOpening(info.wednesdayOpening())
                .thursdayOpening(info.thursdayOpening())
                .fridayOpening(info.fridayOpening())
                .saturdayOpening(info.saturdayOpening())
                .sundayOpening(info.sundayOpening())
                .activated(true)
                .company(company)
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

        if (update.name() != null) {
            establishment.setName(update.name());
        }

        if (update.description() != null) {
            establishment.setDescription(update.description());
        }

        if (update.address() != null) {
            establishment.setAddress(update.address());
        }

        if (update.mondayOpening() != null) {
            establishment.setMondayOpening(update.mondayOpening());
        }

        if (update.tuesdayOpening() != null) {
            establishment.setTuesdayOpening(update.tuesdayOpening());
        }

        if (update.wednesdayOpening() != null) {
            establishment.setWednesdayOpening(update.wednesdayOpening());
        }

        if (update.thursdayOpening() != null) {
            establishment.setThursdayOpening(update.thursdayOpening());
        }

        if (update.fridayOpening() != null) {
            establishment.setFridayOpening(update.fridayOpening());
        }

        if (update.saturdayOpening() != null) {
            establishment.setSaturdayOpening(update.saturdayOpening());
        }

        if (update.sundayOpening() != null) {
            establishment.setSundayOpening(update.sundayOpening());
        }

        return establishmentRepository.save(establishment);
    }

    @SearchCompany
    @SearchEstablishment
    @CheckCompanyCoherence
    @Override
    public boolean activateEstablishment(@CompanyId String companyId, @EstablishmentId String establishmentId) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();

        if (!establishment.isActivated()) {
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

        if (establishment.isActivated()) {
            establishment.setActivated(false);
            establishmentRepository.save(establishment);
            return true;
        }
        return false;
    }
}
