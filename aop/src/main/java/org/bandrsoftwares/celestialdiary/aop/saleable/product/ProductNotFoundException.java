package org.bandrsoftwares.celestialdiary.aop.saleable.product;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String productId) {
        super("The product with the id " + productId + " has not be found");
    }
}
