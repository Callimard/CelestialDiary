package org.bandrsoftwares.celestialdiary.model.dto.saleable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.Saleable;

@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WrappedSaleableDTO {

    // Variables.

    private String id;
    private String name;
    private String description;
    private Double suggestedPrice;

    // Constructors.

    public WrappedSaleableDTO() {}

    public WrappedSaleableDTO(Saleable saleable) {
        this(saleable.getId(), saleable.getName(), saleable.getDescription(), saleable.getSuggestedPrice());
    }
}
