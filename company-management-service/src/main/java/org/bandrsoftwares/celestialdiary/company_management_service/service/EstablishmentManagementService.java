package org.bandrsoftwares.celestialdiary.company_management_service.service;

import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.establishment.EstablishmentId;
import org.bandrsoftwares.celestialdiary.model.general.Address;
import org.bandrsoftwares.celestialdiary.model.general.DatedTimeIntervalList;
import org.bandrsoftwares.celestialdiary.model.general.NonDatedTimeIntervalList;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface EstablishmentManagementService {

    /**
     * @param companyId the company id
     *
     * @return the list of all establishment of the company.
     */
    List<Establishment> allRegisteredEstablishment(String companyId);

    Establishment createEstablishment(@CompanyId String companyId, @Valid EstablishmentCreationInformation establishmentCreationInformation);

    record EstablishmentCreationInformation(@NotNull String name, String description, Address address,
                                            NonDatedTimeIntervalList mondayOpening,
                                            NonDatedTimeIntervalList tuesdayOpening,
                                            NonDatedTimeIntervalList wednesdayOpening,
                                            NonDatedTimeIntervalList thursdayOpening,
                                            NonDatedTimeIntervalList fridayOpening,
                                            NonDatedTimeIntervalList saturdayOpening,
                                            NonDatedTimeIntervalList sundayOpening) {

    }

    Establishment updateEstablishment(@CompanyId String companyId,
                                      @EstablishmentId String establishmentId,
                                      @Valid EstablishmentUpdatedInformation establishmentUpdatedInformation);

    record EstablishmentUpdatedInformation(String name, String description, Address address,
                                           NonDatedTimeIntervalList mondayOpening,
                                           NonDatedTimeIntervalList tuesdayOpening,
                                           NonDatedTimeIntervalList wednesdayOpening,
                                           NonDatedTimeIntervalList thursdayOpening,
                                           NonDatedTimeIntervalList fridayOpening,
                                           NonDatedTimeIntervalList saturdayOpening,
                                           NonDatedTimeIntervalList sundayOpening,
                                           List<DatedTimeIntervalList> exceptionalOpening,
                                           List<DatedTimeIntervalList> exceptionalClosing) {
    }

    boolean activateEstablishment(@CompanyId String companyId, @EstablishmentId String establishmentId);

    boolean deactivateEstablishment(@CompanyId String companyId, @EstablishmentId String establishmentId);
}
