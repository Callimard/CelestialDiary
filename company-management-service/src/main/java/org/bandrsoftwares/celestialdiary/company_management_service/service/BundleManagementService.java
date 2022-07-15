package org.bandrsoftwares.celestialdiary.company_management_service.service;

import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.saleable.bundle.BundleId;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.bundle.Bundle;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface BundleManagementService {

    List<Bundle> allRegisteredBundle(@CompanyId String companyId);

    Bundle createBundle(@CompanyId String companyId, @Valid BundleCreationInformation information);

    record BundleCreationInformation(@NotNull @NotBlank String name, String description, @NotNull @DecimalMin("0.01") Double suggestedPrice,
                                     @NotNull List<String> prestations, @NotNull List<String> products) {
    }


    Bundle updateBundle(@CompanyId String companyId, @BundleId String bundleId, @Valid BundleUpdatedInformation update);

    record BundleUpdatedInformation(String name, String description, Double suggestedPrice,
                                    List<String> prestations, List<String> products) {
    }

    boolean activateBundle(@CompanyId String companyId, @BundleId String bundleId);

    boolean deactivateBundle(@CompanyId String companyId, @BundleId String bundleId);
}
