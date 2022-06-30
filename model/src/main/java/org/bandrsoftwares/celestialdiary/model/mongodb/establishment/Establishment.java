package org.bandrsoftwares.celestialdiary.model.mongodb.establishment;

import lombok.*;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.CompanySummary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Establishment")
public class Establishment {

    @Id
    private String id;

    private String name;
    private String description;

    private CompanySummary companySummary;
}
