package org.bandrsoftwares.celestialdiary.company_management_service.service;

import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.saleable.product.ProductId;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.product.Product;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface ProductManagementService {

    List<Product> allRegisteredProduct(@CompanyId String companyId);

    List<Product> searchProduct(@CompanyId String companyId, String filter);

    Product getSpecificProduct(@CompanyId String companyId, @ProductId String productId);

    Product createProduct(@CompanyId String companyId, @Valid ProductCreationInformation information);

    record ProductCreationInformation(@NotNull @NotBlank String name, String description, @NotNull @DecimalMin("0.01") Double suggestedPrice) {
    }

    Product updateProduct(@CompanyId String companyId, @ProductId String productId, @Valid ProductUpdatedInformation update);

    record ProductUpdatedInformation(String name, String description, Double suggestedPrice) {
    }

    boolean activateProduct(@CompanyId String companyId, @ProductId String productId);

    boolean deactivateProduct(@CompanyId String companyId, @ProductId String productId);
}
