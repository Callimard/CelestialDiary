package org.bandrsoftwares.celestialdiary.company_management_service.service;

import org.bandrsoftwares.celestialdiary.aop.company.CompanyCoherenceException;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.establishment.EstablishmentId;
import org.bandrsoftwares.celestialdiary.aop.establishment.EstablishmentNotFoundException;
import org.bandrsoftwares.celestialdiary.model.dto.general.time.DatedTimeIntervalListDTO;
import org.bandrsoftwares.celestialdiary.model.dto.general.time.NonDatedTimeIntervalListDTO;
import org.bandrsoftwares.celestialdiary.model.general.Address;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface EstablishmentManagementService {

    /**
     * @param companyId the company id
     *
     * @return the list of all establishment of the company.
     */
    List<Establishment> allRegisteredEstablishment(@CompanyId String companyId);

    /**
     * @param companyId the company id
     * @param filter    the filter (either first name)
     *
     * @return the list of establishmentId corresponding to the filter
     */
    List<Establishment> searchEstablishment(@CompanyId String companyId, String filter);


    /**
     * @param companyId       the company id
     * @param establishmentId the establishment id
     *
     * @return the establishment if it has been found and is coherent with the specified company id.
     *
     * @throws EstablishmentNotFoundException if the establishment is not found
     * @throws CompanyCoherenceException      if the establishment id is not coherent with the employee
     */
    Establishment getSpecificEstablishment(@CompanyId String companyId, @EstablishmentId String establishmentId);

    Establishment createEstablishment(@CompanyId String companyId, @Valid EstablishmentCreationInformation establishmentCreationInformation);

    record EstablishmentCreationInformation(@NotNull @NotBlank String name, String description, @NotNull Address address,
                                            NonDatedTimeIntervalListDTO mondayOpening,
                                            NonDatedTimeIntervalListDTO tuesdayOpening,
                                            NonDatedTimeIntervalListDTO wednesdayOpening,
                                            NonDatedTimeIntervalListDTO thursdayOpening,
                                            NonDatedTimeIntervalListDTO fridayOpening,
                                            NonDatedTimeIntervalListDTO saturdayOpening,
                                            NonDatedTimeIntervalListDTO sundayOpening) {

    }

    Establishment updateEstablishment(@CompanyId String companyId,
                                      @EstablishmentId String establishmentId,
                                      @Valid EstablishmentUpdatedInformation establishmentUpdatedInformation);

    record EstablishmentUpdatedInformation(String name, String description, Address address,
                                           NonDatedTimeIntervalListDTO mondayOpening,
                                           NonDatedTimeIntervalListDTO tuesdayOpening,
                                           NonDatedTimeIntervalListDTO wednesdayOpening,
                                           NonDatedTimeIntervalListDTO thursdayOpening,
                                           NonDatedTimeIntervalListDTO fridayOpening,
                                           NonDatedTimeIntervalListDTO saturdayOpening,
                                           NonDatedTimeIntervalListDTO sundayOpening,
                                           List<DatedTimeIntervalListDTO> exceptionalOpening,
                                           List<DatedTimeIntervalListDTO> exceptionalClosing) {
    }

    boolean activateEstablishment(@CompanyId String companyId, @EstablishmentId String establishmentId);

    boolean deactivateEstablishment(@CompanyId String companyId, @EstablishmentId String establishmentId);
}
