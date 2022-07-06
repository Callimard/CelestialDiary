package org.bandrsoftwares.celestialdiary.model.mongodb.general;

import lombok.*;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String street;
    private String zipCode;
    private String city;
    private String country;
}
