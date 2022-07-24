package org.bandrsoftwares.celestialdiary.model.dto.employee;

import lombok.Builder;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.Employee;

@Builder
public record WrappedEmployeeDTO(String id, String email, String firstName, String lastName, String gender, String phone, boolean isTechnician,
                                 boolean activated) {

    public WrappedEmployeeDTO(Employee employee) {
        this(employee.getId(), employee.getEmail(), employee.getFirstName(), employee.getLastName(), employee.getGender().name(),
             employee.getPhone(), employee.getIsTechnician(), employee.getActivated());
    }
}
