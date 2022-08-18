package org.bandrsoftwares.celestialdiary.model.dto.establishment;

import org.bandrsoftwares.celestialdiary.model.dto.equipment.EquipmentDTO;

import java.util.List;

public record AdvancedEstablishmentEquipmentContainerDTO(EquipmentDTO equipment, List<EstablishmentEquipmentDTO> establishmentEquipments) {
}
