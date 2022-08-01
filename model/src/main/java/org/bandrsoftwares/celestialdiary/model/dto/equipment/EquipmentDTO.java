package org.bandrsoftwares.celestialdiary.model.dto.equipment;

import org.bandrsoftwares.celestialdiary.model.mongodb.equipment.Equipment;

public record EquipmentDTO(String id, String name, String description, String photo, String creationDate) {

    public EquipmentDTO(Equipment equipment) {
        this(equipment.getId(), equipment.getName(), equipment.getDescription(), equipment.getPhoto(),
             equipment.getCreationDate().toString());
    }

}
