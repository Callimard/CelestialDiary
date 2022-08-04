package org.bandrsoftwares.celestialdiary.company_management_service.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.aop.SearchingAspect;
import org.bandrsoftwares.celestialdiary.aop.client.ClientId;
import org.bandrsoftwares.celestialdiary.aop.client.SearchClient;
import org.bandrsoftwares.celestialdiary.aop.company.CheckCompanyCoherence;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.company.SearchCompany;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.person.client.Client;
import org.bandrsoftwares.celestialdiary.model.mongodb.person.client.ClientRepository;
import org.bandrsoftwares.celestialdiary.model.mongodb.person.employee.PersonGender;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Validated
@Service
public class ClientManagementServiceImpl implements ClientManagementService {

    // Variables.

    private final ClientRepository clientRepository;

    // Methods.

    @SearchCompany
    @Override
    public List<Client> allRegisteredClient(@CompanyId String companyId) {
        return clientRepository.findByCompany(SearchingAspect.COMPANY_FOUND.get());
    }

    @SearchCompany
    @Override
    public List<Client> searchClient(@CompanyId String companyId, @NonNull String filter) {
        String regexFilter = ".*" + filter + ".*";
        return clientRepository.findByCompanyAndFirstNameRegexOrLastNameRegexOrEmailRegex(SearchingAspect.COMPANY_FOUND.get(), filter, filter,
                                                                                          regexFilter);
    }

    @SearchClient
    @CheckCompanyCoherence
    @Override
    public Client getSpecificClient(@CompanyId String companyId, @ClientId String clientId) {
        return SearchingAspect.CLIENT_FOUND.get();
    }

    @SearchCompany
    @Override
    public Client createClient(@CompanyId String companyId, @Valid ClientCreationInformation clientCreationInformation) {
        return clientRepository.insert(createClientFrom(SearchingAspect.COMPANY_FOUND.get(), clientCreationInformation));
    }

    private Client createClientFrom(Company company, ClientCreationInformation info) {
        return Client.builder()
                .email(info.email())
                .firstName(info.firstName())
                .lastName(info.lastName())
                .comment(info.comment())
                .gender(info.gender() != null ? info.gender() : PersonGender.NO_GENDER)
                .phone(info.phone())
                .origin(info.origin())
                .technicalComment(info.technicalComment())
                .company(company)
                .creationDate(Instant.now())
                .build();
    }

    @SearchClient
    @CheckCompanyCoherence
    @Override
    public Client updateClientInformation(@CompanyId String companyId, @ClientId String clientId,
                                          @Valid ClientUpdatedInformation update) {
        Client client = SearchingAspect.CLIENT_FOUND.get();

        if (update.email() != null) {
            client.setEmail(update.email());
        }

        if (update.firstName() != null) {
            client.setFirstName(update.firstName());
        }

        if (update.lastName() != null) {
            client.setLastName(update.lastName());
        }

        client.setComment(update.comment());

        if (update.gender() != null) {
            client.setGender(update.gender());
        }

        if (update.phone() != null) {
            client.setPhone(update.phone());
        } else {
            client.setPhone(null);
        }

        client.setOrigin(update.origin());
        client.setTechnicalComment(update.technicalComment());

        return clientRepository.save(client);
    }
}
