package org.bandrsoftwares.celestialdiary.aop.establishment;

public class EstablishmentNotFoundException extends RuntimeException {
    public EstablishmentNotFoundException(String establishmentId) {
        super("The establishment with the id " + establishmentId + " has not be found");
    }
}
