package org.bandrsoftwares.celestialdiary.aop.saleable.bundle;

public class BundleNotFoundException extends RuntimeException{
    public BundleNotFoundException(String bundleId) {
        super("The bundle with the id " + bundleId + " has not be found");
    }
}
