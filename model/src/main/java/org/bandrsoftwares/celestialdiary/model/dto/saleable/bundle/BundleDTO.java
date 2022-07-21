package org.bandrsoftwares.celestialdiary.model.dto.saleable.bundle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bandrsoftwares.celestialdiary.model.dto.saleable.SaleableDTO;
import org.bandrsoftwares.celestialdiary.model.dto.saleable.prestation.WrappedPrestationDTO;
import org.bandrsoftwares.celestialdiary.model.dto.saleable.product.WrappedProductDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.bundle.Bundle;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BundleDTO extends SaleableDTO {

    private List<WrappedPrestationDTO> prestations;
    private List<WrappedProductDTO> products;

    public BundleDTO(Bundle bundle) {
        super(bundle.getId(), bundle.getName(), bundle.getDescription(), bundle.getSuggestedPrice(), bundle.getActivated(),
              bundle.getCreationDate().toString());
        this.prestations = bundle.getPrestations() != null ?
                bundle.getPrestations().stream().map(ePrestation -> new WrappedPrestationDTO(ePrestation.getPrestation())).toList() : null;
        this.products = bundle.getProducts() != null ?
                bundle.getProducts().stream().map(eProduct -> new WrappedProductDTO(eProduct.getProduct())).toList() : null;
    }
}
