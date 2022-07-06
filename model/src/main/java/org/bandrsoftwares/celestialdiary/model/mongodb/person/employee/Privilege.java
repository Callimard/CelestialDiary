package org.bandrsoftwares.celestialdiary.model.mongodb.person.employee;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Privilege {

    private String identifierName;
    private String name;
    private String description;
}
