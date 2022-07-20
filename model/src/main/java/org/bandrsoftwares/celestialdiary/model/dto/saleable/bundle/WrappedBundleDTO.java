package org.bandrsoftwares.celestialdiary.model.dto.saleable.bundle;

import org.bandrsoftwares.celestialdiary.model.dto.saleable.WrappedSaleableDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.Saleable;


public class WrappedBundleDTO extends WrappedSaleableDTO {

    public WrappedBundleDTO() {
        super();
    }

    public WrappedBundleDTO(Saleable saleable) {
        super(saleable);
    }
}
