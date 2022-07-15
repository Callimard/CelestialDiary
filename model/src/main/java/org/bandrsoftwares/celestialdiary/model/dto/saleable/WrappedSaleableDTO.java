package org.bandrsoftwares.celestialdiary.model.dto.saleable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.Saleable;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WrappedSaleableDTO {

    // Variables.

    private final String id;
    private final String name;
    private final String description;
    private final Double suggestedPrice;

    // Constructors.

    public WrappedSaleableDTO(Saleable saleable) {
        this(saleable.getId(), saleable.getName(), saleable.getDescription(), saleable.getSuggestedPrice());
    }
}
