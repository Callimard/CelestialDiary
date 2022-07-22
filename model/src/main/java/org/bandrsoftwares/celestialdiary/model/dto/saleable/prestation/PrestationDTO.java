package org.bandrsoftwares.celestialdiary.model.dto.saleable.prestation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bandrsoftwares.celestialdiary.model.dto.saleable.SaleableDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.prestation.Prestation;

@Getter
@Setter
@NoArgsConstructor
public class PrestationDTO extends SaleableDTO {

    private int nbNeededTechnician;
    private int nbClient;
    private int suggestedExecutionTime;

    public PrestationDTO(Prestation prestation) {
        super(prestation.getId(), prestation.getName(), prestation.getDescription(), prestation.getSuggestedPrice(), prestation.getActivated(),
              prestation.getCreationDate().toString());

        this.nbNeededTechnician = prestation.getNbNeededTechnician();
        this.nbClient = prestation.getNbClient();
        this.suggestedExecutionTime = prestation.getSuggestedExecutionTime();
    }
}
