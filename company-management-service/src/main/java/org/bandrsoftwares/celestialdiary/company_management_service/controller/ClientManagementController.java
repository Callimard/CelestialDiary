package org.bandrsoftwares.celestialdiary.company_management_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.company_management_service.service.ClientManagementService;
import org.bandrsoftwares.celestialdiary.model.dto.person.client.ClientDTO;
import org.bandrsoftwares.celestialdiary.security.privilege.company.client.CreateClientPrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.company.client.ReadClientPrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.company.client.UpdateClientPrivilege;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.bandrsoftwares.celestialdiary.api.v1.ApiCompanyV1.CLIENTS_URL;
import static org.bandrsoftwares.celestialdiary.api.v1.ApiCompanyV1.SPECIFIC_CLIENT;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(CLIENTS_URL)
public class ClientManagementController {

    // Variables.

    private final ClientManagementService clientManagementService;

    // Methods.

    @ReadClientPrivilege
    @GetMapping
    public List<ClientDTO> getClients(@PathVariable(name = "idCompany") String idCompany,
                                      @RequestParam(name = "filter", required = false) String filter) {
        if (filter == null) {
            return clientManagementService.allRegisteredClient(idCompany).stream().map(ClientDTO::new).toList();
        } else {
            return clientManagementService.searchClient(idCompany, filter).stream().map(ClientDTO::new).toList();
        }
    }

    @CreateClientPrivilege
    @PostMapping
    public ClientDTO createClient(@PathVariable(name = "idCompany") String idCompany,
                                  @RequestBody ClientManagementService.ClientCreationInformation clientCreationInformation) {
        return new ClientDTO(clientManagementService.createClient(idCompany, clientCreationInformation));
    }

    @ReadClientPrivilege
    @GetMapping(SPECIFIC_CLIENT)
    public ClientDTO getSpecificClient(@PathVariable(name = "idCompany") String idCompany,
                                       @PathVariable(name = "idClient") String idClient) {
        return new ClientDTO(clientManagementService.getSpecificClient(idCompany, idClient));
    }

    @UpdateClientPrivilege
    @PutMapping(SPECIFIC_CLIENT)
    public ClientDTO updateClient(@PathVariable(name = "idCompany") String idCompany,
                                  @PathVariable(name = "idClient") String idClient,
                                  @RequestBody ClientManagementService.ClientUpdatedInformation updatedInformation) {
        return new ClientDTO(clientManagementService.updateClientInformation(idCompany, idClient, updatedInformation));
    }
}
