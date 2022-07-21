package org.bandrsoftwares.celestialdiary.company_management_service.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.company_management_service.service.PrestationManagementService;
import org.bandrsoftwares.celestialdiary.model.dto.saleable.prestation.PrestationDTO;
import org.bandrsoftwares.celestialdiary.model.dto.saleable.prestation.WrappedPrestationDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.prestation.Prestation;
import org.bandrsoftwares.celestialdiary.security.privilege.company.saleable.ActivateSaleablePrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.company.saleable.CreateSaleablePrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.company.saleable.ReadSaleablePrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.company.saleable.UpdateSaleablePrivilege;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.bandrsoftwares.celestialdiary.api.v1.ApiCompanyV1.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(PRESTATION_URL)
public class PrestationManagementController {

    // Variables.

    private final PrestationManagementService prestationManagementService;

    // Methods.

    @ReadSaleablePrivilege
    @GetMapping
    public List<WrappedPrestationDTO> getPrestations(@PathVariable(name = "idCompany") String idCompany,
                                                     @RequestParam(name = "filter", required = false) String filter) {
        if (filter == null) {
            return prestationManagementService.allRegisteredPrestation(idCompany).stream().map(WrappedPrestationDTO::new).toList();
        } else {
            return prestationManagementService.searchPrestation(idCompany, filter).stream().map(WrappedPrestationDTO::new).toList();
        }
    }

    @CreateSaleablePrivilege
    @PostMapping
    public WrappedPrestationDTO createPrestation(@PathVariable(name = "idCompany") String idCompany,
                                                 @RequestBody PrestationManagementService.PrestationCreationInformation information) {
        Prestation prestation = prestationManagementService.createPrestation(idCompany, information);
        return new WrappedPrestationDTO(prestation);
    }

    @ReadSaleablePrivilege
    @GetMapping(SPECIFIC_PRESTATION)
    public PrestationDTO getSpecificPrestation(@PathVariable(name = "idCompany") String idCompany,
                                               @PathVariable(name = "idPrestation") String idPrestation) {
        return new PrestationDTO(prestationManagementService.getSpecificPrestation(idCompany, idPrestation));
    }

    @UpdateSaleablePrivilege
    @PutMapping(SPECIFIC_PRESTATION)
    public WrappedPrestationDTO updatePrestation(@PathVariable(name = "idCompany") String idCompany,
                                                 @PathVariable(name = "idPrestation") String idPrestation,
                                                 @RequestBody PrestationManagementService.PrestationUpdatedInformation update) {
        Prestation prestation = prestationManagementService.updatePrestation(idCompany, idPrestation, update);
        return new WrappedPrestationDTO(prestation);
    }

    @ActivateSaleablePrivilege
    @PutMapping(SPECIFIC_PRESTATION_ACTIVATION)
    public boolean activatePrestation(@PathVariable(name = "idCompany") String idCompany,
                                      @PathVariable(name = "idPrestation") String idPrestation) {
        return prestationManagementService.activatePrestation(idCompany, idPrestation);
    }

    @ActivateSaleablePrivilege
    @DeleteMapping(SPECIFIC_PRESTATION_ACTIVATION)
    public boolean deactivatePrestation(@PathVariable(name = "idCompany") String idCompany,
                                        @PathVariable(name = "idPrestation") String idPrestation) {
        return prestationManagementService.deactivatePrestation(idCompany, idPrestation);
    }
}
