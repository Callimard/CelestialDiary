package org.bandrsoftwares.celestialdiary.company_management_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.company_management_service.service.ProductManagementService;
import org.bandrsoftwares.celestialdiary.model.dto.saleable.product.ProductDTO;
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
@RequestMapping(PRODUCTS_URL)
public class ProductManagementController {

    // Variables.

    private final ProductManagementService productManagementService;

    // Methods.

    @ReadSaleablePrivilege
    @GetMapping
    public List<WrappedProductDTO> getProducts(@PathVariable(name = "idCompany") String idCompany,
                                               @RequestParam(name = "filter", required = false) String filter) {
        if (filter == null) {
            return productManagementService.allRegisteredProduct(idCompany).stream().map(WrappedProductDTO::new).toList();
        } else {
            return productManagementService.searchProduct(idCompany, filter).stream().map(WrappedProductDTO::new).toList();
        }
    }

    @CreateSaleablePrivilege
    @PostMapping
    public WrappedProductDTO createProduct(@PathVariable(name = "idCompany") String idCompany,
                                           @RequestBody ProductManagementService.ProductCreationInformation information) {
        Product product = productManagementService.createProduct(idCompany, information);
        return new WrappedProductDTO(product);
    }

    @ReadSaleablePrivilege
    @GetMapping(SPECIFIC_PRODUCT)
    public ProductDTO getSpecificProduct(@PathVariable(name = "idCompany") String idCompany,
                                         @PathVariable(name = "idProduct") String idProduct) {
        return new ProductDTO(productManagementService.getSpecificProduct(idCompany, idProduct));
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
