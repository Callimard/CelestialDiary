package org.bandrsoftwares.celestialdiary.model.mongodb.company;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.Instant;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class CompanySummary {

    private String companyEmail;
    private String companyName;
    private Instant companyCreationDate;

    @ToString.Exclude
    @DocumentReference(collection = "Company", lazy = true)
    private Company company;
}
