package org.bandrsoftwares.celestialdiary.model.mongodb.establishment;

import lombok.*;
import org.bandrsoftwares.celestialdiary.model.mongodb.equipment.Equipment;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Objects;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentEquipment {

    @NonNull
    private Integer quantity;

    @NonNull
    private Integer numberUnusable;

    @ToString.Exclude
    @DocumentReference(collection = "Equipment", lazy = true)
    @NonNull
    private Equipment equipment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EstablishmentEquipment that)) return false;
        return equipment.getId().equals(that.equipment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipment.getId());
    }
}
