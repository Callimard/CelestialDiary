package org.bandrsoftwares.celestialdiary.model.dto.saleable.prestation;

import lombok.Getter;
import lombok.Setter;
import org.bandrsoftwares.celestialdiary.model.dto.saleable.WrappedSaleableDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.prestation.Prestation;

@Setter
@Getter
public class WrappedPrestationDTO extends WrappedSaleableDTO {

    // Variables.

    private Integer nbNeededTechnician;
    private Integer nbClient;

    private Integer suggestedExecutionTime;

    // Constructors.

    public WrappedPrestationDTO() {
        super();
    }

    public WrappedPrestationDTO(Prestation prestation) {
        super(prestation);

        this.nbNeededTechnician = prestation.getNbNeededTechnician();
        this.nbClient = prestation.getNbClient();
        this.suggestedExecutionTime = prestation.getSuggestedExecutionTime();
    }
}
