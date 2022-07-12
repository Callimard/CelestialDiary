package org.bandrsoftwares.celestialdiary.company_management_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.company_management_service.service.EstablishmentManagementService;
import org.bandrsoftwares.celestialdiary.model.dto.establishment.WrappedEstablishmentDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.bandrsoftwares.celestialdiary.security.privilege.company.establishment.ActivateEstablishmentPrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.company.establishment.CreateEstablishmentPrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.company.establishment.ReadEstablishmentPrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.company.establishment.UpdateEstablishmentPrivilege;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.bandrsoftwares.celestialdiary.api.v1.ApiCompanyV1.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(ESTABLISHMENT_URL)
public class EstablishmentManagementController {

    // Variables.

    private final EstablishmentManagementService establishmentManagementService;

    // Methods.

    @ReadEstablishmentPrivilege
    @GetMapping
    public List<WrappedEstablishmentDTO> getEstablishments(@PathVariable(name = "idCompany") String idCompany) {
        List<Establishment> establishments = establishmentManagementService.allRegisteredEstablishment(idCompany);
        return establishments.stream().map(WrappedEstablishmentDTO::new).toList();
    }

    @CreateEstablishmentPrivilege
    @PostMapping
    public WrappedEstablishmentDTO createEstablishment(@PathVariable(name = "idCompany") String idCompany, @RequestBody
            EstablishmentManagementService.EstablishmentCreationInformation information) {
        Establishment establishment = establishmentManagementService.createEstablishment(idCompany, information);
        return new WrappedEstablishmentDTO(establishment);
    }

    @UpdateEstablishmentPrivilege
    @PutMapping(SPECIFIC_ESTABLISHMENT)
    public WrappedEstablishmentDTO updateEstablishment(@PathVariable(name = "idCompany") String idCompany,
                                                       @PathVariable(name = "idEstablishment") String idEstablishment,
                                                       @RequestBody EstablishmentManagementService.EstablishmentUpdatedInformation update) {
        Establishment establishment = establishmentManagementService.updateEstablishment(idCompany, idEstablishment, update);
        return new WrappedEstablishmentDTO(establishment);
    }

    @ActivateEstablishmentPrivilege
    @PutMapping(SPECIFIC_ESTABLISHMENT_ACTIVATION)
    public boolean activateEstablishment(@PathVariable(name = "idCompany") String idCompany,
                                         @PathVariable(name = "idEstablishment") String idEstablishment) {
        return establishmentManagementService.activateEstablishment(idCompany, idEstablishment);
    }

    @ActivateEstablishmentPrivilege
    @DeleteMapping(SPECIFIC_ESTABLISHMENT_ACTIVATION)
    public boolean deactivateEstablishment(@PathVariable(name = "idCompany") String idCompany,
                                           @PathVariable(name = "idEstablishment") String idEstablishment) {
        return establishmentManagementService.deactivateEstablishment(idCompany, idEstablishment);
    }
}
