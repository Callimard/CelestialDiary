package org.bandrsoftwares.celestialdiary.aop.client;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String clientId) {
        super("The client with the id " + clientId + " has not be found");
    }
}
