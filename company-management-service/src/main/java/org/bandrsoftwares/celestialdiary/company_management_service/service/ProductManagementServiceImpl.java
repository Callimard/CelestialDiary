package org.bandrsoftwares.celestialdiary.company_management_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.aop.SearchingAspect;
import org.bandrsoftwares.celestialdiary.aop.company.CheckCompanyCoherence;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.company.SearchCompany;
import org.bandrsoftwares.celestialdiary.aop.saleable.product.ProductId;
import org.bandrsoftwares.celestialdiary.aop.saleable.product.SearchProduct;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.product.Product;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Validated
@Service
public class ProductManagementServiceImpl implements ProductManagementService {

    // Variables.

    private final ProductRepository productRepository;

    // Methods.

    @SearchCompany
    @Override
    public List<Product> allRegisteredProduct(@CompanyId String companyId) {
        return productRepository.findByCompany(SearchingAspect.COMPANY_FOUND.get());
    }

    @SearchCompany
    @Override
    public Product createProduct(@CompanyId String companyId, @Valid ProductCreationInformation information) {
        Product product = createProductFrom(SearchingAspect.COMPANY_FOUND.get(), information);
        return productRepository.insert(product);
    }

    private Product createProductFrom(Company company, ProductCreationInformation info) {
        return Product.builder()
                .name(info.name())
                .description(info.description())
                .suggestedPrice(info.suggestedPrice())
                .activated(true)
                .creationDate(Instant.now())
                .company(company)
                .build();
    }

    @SearchProduct
    @CheckCompanyCoherence
    @Override
    public Product updateProduct(@CompanyId String companyId, @ProductId String productId, @Valid ProductUpdatedInformation update) {
        Product product = SearchingAspect.PRODUCT_FOUND.get();

        if (update.name() != null && !update.name().isBlank()) {
            product.setName(update.name());
        }

        if (update.description() != null) {
            product.setDescription(update.description());
        }

        if (update.suggestedPrice() != null) {
            product.setSuggestedPrice(update.suggestedPrice());
        }

        return productRepository.save(product);
    }

    @SearchProduct
    @CheckCompanyCoherence
    @Override
    public boolean activateProduct(@CompanyId String companyId, @ProductId String productId) {
        Product product = SearchingAspect.PRODUCT_FOUND.get();

        if (Boolean.FALSE.equals(product.getActivated())) {
            product.setActivated(true);
            productRepository.save(product);
            return true;
        }
        return false;
    }

    @SearchProduct
    @CheckCompanyCoherence
    @Override
    public boolean deactivateProduct(@CompanyId String companyId, @ProductId String productId) {
        Product product = SearchingAspect.PRODUCT_FOUND.get();

        if (Boolean.TRUE.equals(product.getActivated())) {
            product.setActivated(false);
            productRepository.save(product);
            return true;
        }
        return false;
    }
}
