package org.bandrsoftwares.celestialdiary.model.dto.saleable.prestation;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bandrsoftwares.celestialdiary.model.dto.equipment.EquipmentDTO;
import org.bandrsoftwares.celestialdiary.model.dto.saleable.SaleableDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.prestation.Prestation;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PrestationDTO extends SaleableDTO {

    private int nbNeededTechnician;
    private int nbClient;
    private int suggestedExecutionTime;
    private List<EquipmentDTO> neededEquipments;

    public PrestationDTO(Prestation prestation) {
        super(prestation.getId(), prestation.getName(), prestation.getDescription(), prestation.getSuggestedPrice(), prestation.getActivated(),
              prestation.getCreationDate().toString());

        this.nbNeededTechnician = prestation.getNbNeededTechnician();
        this.nbClient = prestation.getNbClient();
        this.suggestedExecutionTime = prestation.getSuggestedExecutionTime();
        this.neededEquipments = prestation.getNeededEquipments() != null ?
                prestation.getNeededEquipments().stream().map(EquipmentDTO::new).toList() : Lists.newArrayList();
    }
}
