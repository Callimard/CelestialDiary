package org.bandrsoftwares.celestialdiary.company_management_service.service;

import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.saleable.service.PrestationId;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.prestation.Prestation;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface PrestationManagementService {

    List<Prestation> allRegisteredPrestation(@CompanyId String companyId);

    List<Prestation> searchPrestation(@CompanyId String companyId, String filter);

    Prestation getSpecificPrestation(@CompanyId String companyId, @PrestationId String prestationId);

    Prestation createPrestation(@CompanyId String companyId, @Valid PrestationCreationInformation information);

    record PrestationCreationInformation(@NotNull @NotBlank String name, String description, @NotNull @DecimalMin("0.01") Double suggestedPrice,
                                         @NotNull @Min(1) Integer nbNeededTechnician, @NotNull @Min(1) Integer nbClient,
                                         @NotNull @Min(1) Integer suggestedExecutionTime, List<String> neededEquipments) {
    }

    Prestation updatePrestation(@CompanyId String companyId, @PrestationId String prestationId,
                                @Valid PrestationUpdatedInformation update);

    record PrestationUpdatedInformation(String name, String description, Double suggestedPrice,
                                        Integer nbNeededTechnician, Integer nbClient,
                                        Integer suggestedExecutionTime, List<String> neededEquipments) {
    }

    boolean activatePrestation(@CompanyId String companyId, @PrestationId String prestationId);

    boolean deactivatePrestation(@CompanyId String companyId, @PrestationId String prestationId);
}
