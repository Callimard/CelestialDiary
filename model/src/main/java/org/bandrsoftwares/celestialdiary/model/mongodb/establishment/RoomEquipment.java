package org.bandrsoftwares.celestialdiary.model.mongodb.establishment;

import lombok.*;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomEquipment {

    @NonNull
    private Integer weight;

    private String establishmentEquipmentId;
}
