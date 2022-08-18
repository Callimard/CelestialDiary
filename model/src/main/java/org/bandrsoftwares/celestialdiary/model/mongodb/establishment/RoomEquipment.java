package org.bandrsoftwares.celestialdiary.model.mongodb.establishment;

import lombok.*;
import org.bandrsoftwares.celestialdiary.model.mongodb.equipment.Equipment;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomEquipment {

    @NonNull
    private Integer weight;

    @NonNull
    @ToString.Exclude
    @DocumentReference(collection = "Equipment", lazy = true)
    private Equipment equipment;
}
