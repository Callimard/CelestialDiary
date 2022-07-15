package org.bandrsoftwares.celestialdiary.company_management_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.aop.SearchingAspect;
import org.bandrsoftwares.celestialdiary.aop.company.CheckCompanyCoherence;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.company.SearchCompany;
import org.bandrsoftwares.celestialdiary.aop.saleable.bundle.BundleId;
import org.bandrsoftwares.celestialdiary.aop.saleable.bundle.SearchBundle;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.bundle.Bundle;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.bundle.BundleRepository;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.prestation.EmbeddedPrestation;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.prestation.Prestation;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.prestation.PrestationRepository;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.product.EmbeddedProduct;
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
public class BundleManagementServiceImpl implements BundleManagementService {

    // Variables.

    private final BundleRepository bundleRepository;
    private final PrestationRepository prestationRepository;
    private final ProductRepository productRepository;

    // Methods.

    @SearchCompany
    @Override
    public List<Bundle> allRegisteredBundle(@CompanyId String companyId) {
        return bundleRepository.findByCompany(SearchingAspect.COMPANY_FOUND.get());
    }

    @SearchCompany
    @Override
    public Bundle createBundle(@CompanyId String companyId, @Valid BundleCreationInformation information) {
        Bundle bundle = createBundleFrom(SearchingAspect.COMPANY_FOUND.get(), information);
        return bundleRepository.insert(bundle);
    }

    private Bundle createBundleFrom(Company company, BundleCreationInformation info) {
        List<Prestation> prestations = prestationRepository.findByCompanyAndIdIn(company, info.prestations());
        List<Product> products = productRepository.findByCompanyAndIdIn(company, info.products());

        return Bundle.builder()
                .name(info.name())
                .description(info.description())
                .suggestedPrice(info.suggestedPrice())
                .prestations(prestations.stream().map(EmbeddedPrestation::from).toList())
                .products(products.stream().map(EmbeddedProduct::from).toList())
                .activated(true)
                .creationDate(Instant.now())
                .company(company)
                .build();
    }

    @SearchCompany
    @SearchBundle
    @CheckCompanyCoherence
    @Override
    public Bundle updateBundle(@CompanyId String companyId, @BundleId String bundleId, @Valid BundleUpdatedInformation update) {
        Company company = SearchingAspect.COMPANY_FOUND.get();
        Bundle bundle = SearchingAspect.BUNDLE_FOUND.get();

        if (update.name() != null && !update.name().isBlank()) {
            bundle.setName(update.name());
        }

        if (update.description() != null) {
            bundle.setDescription(update.description());
        }

        if (update.suggestedPrice() != null) {
            bundle.setSuggestedPrice(update.suggestedPrice());
        }

        if (update.prestations() != null && !update.prestations().isEmpty()) {
            List<Prestation> prestations = prestationRepository.findByCompanyAndIdIn(company, update.prestations());
            bundle.setPrestations(prestations.stream().map(EmbeddedPrestation::from).toList());
        }

        if (update.products() != null && !update.products().isEmpty()) {
            List<Product> products = productRepository.findByCompanyAndIdIn(company, update.products());
            bundle.setProducts(products.stream().map(EmbeddedProduct::from).toList());
        }

        return bundleRepository.save(bundle);
    }

    @SearchBundle
    @CheckCompanyCoherence
    @Override
    public boolean activateBundle(@CompanyId String companyId, @BundleId String bundleId) {
        Bundle bundle = SearchingAspect.BUNDLE_FOUND.get();

        if (Boolean.FALSE.equals(bundle.getActivated())) {
            bundle.setActivated(true);
            bundleRepository.save(bundle);
            return true;
        }
        return false;
    }

    @SearchBundle
    @CheckCompanyCoherence
    @Override
    public boolean deactivateBundle(@CompanyId String companyId, @BundleId String bundleId) {
        Bundle bundle = SearchingAspect.BUNDLE_FOUND.get();

        if (Boolean.TRUE.equals(bundle.getActivated())) {
            bundle.setActivated(false);
            bundleRepository.save(bundle);
            return true;
        }
        return false;
    }
}
