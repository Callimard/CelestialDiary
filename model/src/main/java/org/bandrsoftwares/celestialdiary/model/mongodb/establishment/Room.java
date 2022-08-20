package org.bandrsoftwares.celestialdiary.model.mongodb.establishment;

import lombok.*;

import java.util.Objects;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    // Unique in the establishment and us as id.
    @NonNull
    private String name;

    @NonNull
    private Integer capacity;

    private String photo;

    @NonNull
    private Boolean available;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room room)) return false;
        return name.equals(room.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
