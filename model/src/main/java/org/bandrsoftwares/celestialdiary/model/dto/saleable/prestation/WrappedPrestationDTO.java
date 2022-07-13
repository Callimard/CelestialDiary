package org.bandrsoftwares.celestialdiary.model.dto.saleable.prestation;

import lombok.Getter;
import org.bandrsoftwares.celestialdiary.model.dto.saleable.WrappedSaleableDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.prestation.Prestation;

@Getter
public class WrappedPrestationDTO extends WrappedSaleableDTO {

    // Variables.

    private final Integer nbNeededTechnician;
    private final Integer nbClient;

    private final Integer suggestedExecutionTime;

    // Constructors.

    public WrappedPrestationDTO(Prestation prestation) {
        super(prestation);

        this.nbNeededTechnician = prestation.getNbNeededTechnician();
        this.nbClient = prestation.getNbClient();
        this.suggestedExecutionTime = prestation.getSuggestedExecutionTime();
    }
}
