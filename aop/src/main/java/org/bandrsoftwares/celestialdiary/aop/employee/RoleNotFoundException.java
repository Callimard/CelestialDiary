package org.bandrsoftwares.celestialdiary.aop.employee;

public class RoleNotFoundException extends RuntimeException{
    public RoleNotFoundException(String roleId) {
        super("The role with the id " + roleId + " has not be found");
    }
}
