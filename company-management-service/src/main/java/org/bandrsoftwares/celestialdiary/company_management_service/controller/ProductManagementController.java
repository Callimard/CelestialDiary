package org.bandrsoftwares.celestialdiary.company_management_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.company_management_service.service.ProductManagementService;
import org.bandrsoftwares.celestialdiary.model.dto.saleable.product.WrappedProductDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.product.Product;
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
@RequestMapping(PRODUCT_URL)
public class ProductManagementController {

    // Variables.

    private final ProductManagementService productManagementService;

    // Methods.

    @ReadSaleablePrivilege
    @GetMapping
    public List<WrappedProductDTO> getProducts(@PathVariable(name = "idCompany") String idCompany) {
        List<Product> products = productManagementService.allRegisteredProduct(idCompany);
        return products.stream().map(WrappedProductDTO::new).toList();
    }

    @CreateSaleablePrivilege
    @PostMapping
    public WrappedProductDTO createProduct(@PathVariable(name = "idCompany") String idCompany,
                                           @RequestBody ProductManagementService.ProductCreationInformation information) {
        Product product = productManagementService.createProduct(idCompany, information);
        return new WrappedProductDTO(product);
    }

    @UpdateSaleablePrivilege
    @PutMapping(SPECIFIC_PRODUCT)
    public WrappedProductDTO updateProduct(@PathVariable(name = "idCompany") String idCompany,
                                           @PathVariable(name = "idProduct") String idProduct,
                                           @RequestBody ProductManagementService.ProductUpdatedInformation update) {
        Product product = productManagementService.updateProduct(idCompany, idProduct, update);
        return new WrappedProductDTO(product);
    }

    @ActivateSaleablePrivilege
    @PutMapping(SPECIFIC_PRODUCT_ACTIVATION)
    public boolean activateProduct(@PathVariable(name = "idCompany") String idCompany,
                                   @PathVariable(name = "idProduct") String idProduct) {
        return productManagementService.activateProduct(idCompany, idProduct);
    }

    @ActivateSaleablePrivilege
    @DeleteMapping(SPECIFIC_PRODUCT_ACTIVATION)
    public boolean deactivateProduct(@PathVariable(name = "idCompany") String idCompany,
                                     @PathVariable(name = "idProduct") String idProduct) {
        return productManagementService.deactivateProduct(idCompany, idProduct);
    }
}
