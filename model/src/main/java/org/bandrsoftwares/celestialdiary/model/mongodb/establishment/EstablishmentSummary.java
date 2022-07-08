package org.bandrsoftwares.celestialdiary.model.mongodb.establishment;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Objects;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentSummary {

    @NonNull
    private String establishmentName;

    @NonNull
    @ToString.Exclude
    @DocumentReference(collection = "Establishment", lazy = true)
    private Establishment establishment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EstablishmentSummary that)) return false;
        return establishmentName.equals(that.establishmentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(establishmentName);
    }
}
