package org.bandrsoftwares.celestialdiary.model.mongodb.employee;

import lombok.Builder;

@Builder
public record WrappedEmployeeDTO(String id, String email, String firstName, String lastName, String gender, String phone, boolean isTechnician,
                                 boolean isActive) {

    public WrappedEmployeeDTO(Employee employee) {
        this(employee.getId(), employee.getEmail(), employee.getFirstName(), employee.getLastName(), employee.getGender().name(),
             employee.getPhone(), employee.getIsTechnician(), employee.getActive());
    }
}
