package org.bandrsoftwares.celestialdiary.model.mongodb.employee;

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
