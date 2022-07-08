package org.bandrsoftwares.celestialdiary.aop.employee;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String employeeId) {
        super("The employee with the id " + employeeId + " has not be found");
    }
}
