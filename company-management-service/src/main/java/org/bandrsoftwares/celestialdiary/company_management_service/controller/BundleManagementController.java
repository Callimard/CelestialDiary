package org.bandrsoftwares.celestialdiary.company_management_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.company_management_service.service.BundleManagementService;
import org.bandrsoftwares.celestialdiary.model.dto.saleable.bundle.WrappedBundleDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.bundle.Bundle;
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
@RequestMapping(BUNDLE_URL)
public class BundleManagementController {

    // Variables.

    private final BundleManagementService bundleManagementService;

    // Methods.

    @ReadSaleablePrivilege
    @GetMapping
    public List<WrappedBundleDTO> getBundles(@PathVariable(name = "idCompany") String idCompany) {
        List<Bundle> products = bundleManagementService.allRegisteredBundle(idCompany);
        return products.stream().map(WrappedBundleDTO::new).toList();
    }

    @CreateSaleablePrivilege
    @PostMapping
    public WrappedBundleDTO createBundle(@PathVariable(name = "idCompany") String idCompany,
                                         @RequestBody BundleManagementService.BundleCreationInformation information) {
        Bundle bundle = bundleManagementService.createBundle(idCompany, information);
        return new WrappedBundleDTO(bundle);
    }

    @UpdateSaleablePrivilege
    @PutMapping(SPECIFIC_BUNDLE)
    public WrappedBundleDTO updateBundle(@PathVariable(name = "idCompany") String idCompany,
                                         @PathVariable(name = "idBundle") String idBundle,
                                         @RequestBody BundleManagementService.BundleUpdatedInformation update) {
        Bundle bundle = bundleManagementService.updateBundle(idCompany, idBundle, update);
        return new WrappedBundleDTO(bundle);
    }

    @ActivateSaleablePrivilege
    @PutMapping(SPECIFIC_BUNDLE_ACTIVATION)
    public boolean activateBundle(@PathVariable(name = "idCompany") String idCompany,
                                  @PathVariable(name = "idBundle") String idBundle) {
        return bundleManagementService.activateBundle(idCompany, idBundle);
    }

    @ActivateSaleablePrivilege
    @DeleteMapping(SPECIFIC_BUNDLE_ACTIVATION)
    public boolean deactivateBundle(@PathVariable(name = "idCompany") String idCompany,
                                    @PathVariable(name = "idBundle") String idBundle) {
        return bundleManagementService.deactivateBundle(idCompany, idBundle);
    }
}
