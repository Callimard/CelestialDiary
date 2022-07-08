package org.bandrsoftwares.celestialdiary.model.mongodb.company;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.Instant;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanySummary {

    @NonNull
    private String companyEmail;

    @NonNull
    private String companyName;

    @NonNull
    private Instant companyCreationDate;

    @NonNull
    @ToString.Exclude
    @DocumentReference(collection = "Company", lazy = true)
    private Company company;
}
