package org.bandrsoftwares.celestialdiary.company_management_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.company_management_service.service.EstablishmentManagementService;
import org.bandrsoftwares.celestialdiary.model.dto.establishment.EstablishmentDTO;
import org.bandrsoftwares.celestialdiary.model.dto.establishment.WrappedEstablishmentDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.bandrsoftwares.celestialdiary.security.privilege.company.establishment.ActivateCompanyEstablishmentPrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.company.establishment.CreateCompanyEstablishmentPrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.company.establishment.ReadCompanyEstablishmentPrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.company.establishment.UpdateCompanyEstablishmentPrivilege;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.bandrsoftwares.celestialdiary.api.v1.ApiCompanyV1.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(ESTABLISHMENTS_URL)
public class EstablishmentManagementController {

    // Variables.

    private final EstablishmentManagementService establishmentManagementService;

    // Methods.

    @GetMapping
    public List<WrappedEstablishmentDTO> getEstablishments(@PathVariable(name = "idCompany") String idCompany,
                                                           @RequestParam(name = "filter", required = false) String filter,
                                                           @RequestParam(name = "ids", required = false) String ids) {
        if (filter != null) {
            return establishmentManagementService.searchEstablishment(idCompany, filter).stream().map(WrappedEstablishmentDTO::new).toList();
        } else if (ids != null) {
            return establishmentManagementService.searchEstablishment(idCompany, ids.split(";")).stream().map(WrappedEstablishmentDTO::new).toList();
        } else {
            return establishmentManagementService.allRegisteredEstablishment(idCompany).stream().map(WrappedEstablishmentDTO::new).toList();
        }
    }

    @CreateCompanyEstablishmentPrivilege
    @PostMapping
    public WrappedEstablishmentDTO createEstablishment(@PathVariable(name = "idCompany") String idCompany, @RequestBody
            EstablishmentManagementService.EstablishmentCreationInformation information) {
        Establishment establishment = establishmentManagementService.createEstablishment(idCompany, information);
        return new WrappedEstablishmentDTO(establishment);
    }

    @ReadCompanyEstablishmentPrivilege
    @GetMapping(SPECIFIC_ESTABLISHMENT)
    public EstablishmentDTO getSpecificEstablishment(@PathVariable(name = "idCompany") String idCompany,
                                                     @PathVariable(name = "idEstablishment") String idEstablishment) {
        return new EstablishmentDTO(establishmentManagementService.getSpecificEstablishment(idCompany, idEstablishment));
    }

    @UpdateCompanyEstablishmentPrivilege
    @PutMapping(SPECIFIC_ESTABLISHMENT)
    public WrappedEstablishmentDTO updateEstablishment(@PathVariable(name = "idCompany") String idCompany,
                                                       @PathVariable(name = "idEstablishment") String idEstablishment,
                                                       @RequestBody EstablishmentManagementService.EstablishmentUpdatedInformation update) {
        Establishment establishment = establishmentManagementService.updateEstablishment(idCompany, idEstablishment, update);
        return new WrappedEstablishmentDTO(establishment);
    }

    @ActivateCompanyEstablishmentPrivilege
    @PutMapping(SPECIFIC_ESTABLISHMENT_ACTIVATION)
    public boolean activateEstablishment(@PathVariable(name = "idCompany") String idCompany,
                                         @PathVariable(name = "idEstablishment") String idEstablishment) {
        return establishmentManagementService.activateEstablishment(idCompany, idEstablishment);
    }

    @ActivateCompanyEstablishmentPrivilege
    @DeleteMapping(SPECIFIC_ESTABLISHMENT_ACTIVATION)
    public boolean deactivateEstablishment(@PathVariable(name = "idCompany") String idCompany,
                                           @PathVariable(name = "idEstablishment") String idEstablishment) {
        return establishmentManagementService.deactivateEstablishment(idCompany, idEstablishment);
    }
}
