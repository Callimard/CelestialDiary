package org.bandrsoftwares.celestialdiary.model.mongodb.establishment;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class EstablishmentSaleable {

    private Double customPrice;
    private Boolean usingCustomPrice;

}
