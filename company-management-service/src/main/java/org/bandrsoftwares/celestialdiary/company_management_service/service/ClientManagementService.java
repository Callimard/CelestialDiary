package org.bandrsoftwares.celestialdiary.company_management_service.service;

import org.bandrsoftwares.celestialdiary.aop.client.ClientId;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.model.mongodb.person.client.Client;
import org.bandrsoftwares.celestialdiary.model.mongodb.person.employee.PersonGender;
import org.bandrsoftwares.celestialdiary.validation.ValidEmail;
import org.bandrsoftwares.celestialdiary.validation.ValidPhone;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface ClientManagementService {

    List<Client> allRegisteredClient(@CompanyId String companyId);

    List<Client> searchClient(@CompanyId String companyId, String filter);

    Client getSpecificClient(@CompanyId String companyId, @ClientId String clientId);

    Client createClient(@CompanyId String companyId, @Valid ClientCreationInformation clientCreationInformation);

    record ClientCreationInformation(@NotNull @ValidEmail String email,
                                     @NotNull @NotBlank String firstName,
                                     @NotNull @NotBlank String lastName,
                                     String comment,
                                     PersonGender gender,
                                     @ValidPhone String phone,
                                     String origin,
                                     String technicalComment) {
    }

    Client updateClientInformation(@CompanyId String companyId, @ClientId String clientId, @Valid ClientUpdatedInformation clientUpdatedInformation);

    record ClientUpdatedInformation(@ValidEmail String email,
                                    String firstName,
                                    String lastName,
                                    String comment,
                                    PersonGender gender,
                                    @ValidPhone String phone,
                                    String origin,
                                    String technicalComment) {
    }
}
