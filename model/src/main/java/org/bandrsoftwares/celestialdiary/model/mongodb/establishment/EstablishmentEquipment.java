package org.bandrsoftwares.celestialdiary.model.mongodb.establishment;

import lombok.*;

import java.util.Objects;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentEquipment {

    @NonNull
    private String id;

    @NonNull
    private String equipmentId;

    @NonNull
    private String name;

    @NonNull
    private Boolean available;

    private String roomId;

    private String photo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EstablishmentEquipment that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
