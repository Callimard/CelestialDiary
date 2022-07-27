package org.bandrsoftwares.celestialdiary.company_management_service.service;

import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.saleable.bundle.BundleId;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.bundle.Bundle;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public interface BundleManagementService {

    List<Bundle> allRegisteredBundle(@CompanyId String companyId);

    List<Bundle> searchBundle(@CompanyId String companyId, String filter);

    Bundle getSpecificBundle(@CompanyId String companyId, @BundleId String bundleId);

    Bundle createBundle(@CompanyId String companyId, @Valid BundleCreationInformation information);

    record BundleCreationInformation(@NotNull @NotBlank String name, String description, @NotNull @DecimalMin("0.01") Double suggestedPrice,
                                     @NotNull Map<String, Integer> prestations, @NotNull Map<String, Integer> products) {
    }

    Bundle updateBundle(@CompanyId String companyId, @BundleId String bundleId, @Valid BundleUpdatedInformation update);

    record BundleUpdatedInformation(String name, String description, Double suggestedPrice,
                                    Map<String, Integer> prestations, Map<String, Integer> products) {
    }

    boolean activateBundle(@CompanyId String companyId, @BundleId String bundleId);

    boolean deactivateBundle(@CompanyId String companyId, @BundleId String bundleId);
}

