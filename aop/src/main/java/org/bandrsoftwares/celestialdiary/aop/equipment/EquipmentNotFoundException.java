package org.bandrsoftwares.celestialdiary.aop.equipment;

public class EquipmentNotFoundException extends RuntimeException {

    public EquipmentNotFoundException(String equipmentId) {
        super("The equipment with the id " + equipmentId + " has not be found");
    }
}
