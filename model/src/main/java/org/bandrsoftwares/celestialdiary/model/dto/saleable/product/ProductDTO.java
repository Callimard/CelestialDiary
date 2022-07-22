package org.bandrsoftwares.celestialdiary.model.dto.saleable.product;

import lombok.NoArgsConstructor;
import org.bandrsoftwares.celestialdiary.model.dto.saleable.SaleableDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.product.Product;

@NoArgsConstructor
public class ProductDTO extends SaleableDTO {

    public ProductDTO(Product product) {
        super(product.getId(), product.getName(), product.getDescription(), product.getSuggestedPrice(), product.getActivated(),
              product.getCreationDate().toString());
    }
}
