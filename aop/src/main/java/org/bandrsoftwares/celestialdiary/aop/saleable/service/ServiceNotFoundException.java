package org.bandrsoftwares.celestialdiary.aop.saleable.service;

public class ServiceNotFoundException extends RuntimeException {
    public ServiceNotFoundException(String serviceId) {
        super("The service with the id " + serviceId + " has not be found");
    }
}
