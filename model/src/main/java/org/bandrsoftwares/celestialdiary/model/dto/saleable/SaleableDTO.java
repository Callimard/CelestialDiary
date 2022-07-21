package org.bandrsoftwares.celestialdiary.model.dto.saleable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class SaleableDTO {

    private String id;

    private String name;
    private String description;

    private double suggestedPrice;

    private boolean activated;

    private String creationDate;

}
