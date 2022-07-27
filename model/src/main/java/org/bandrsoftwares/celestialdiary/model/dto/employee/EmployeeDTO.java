package org.bandrsoftwares.celestialdiary.model.dto.employee;

import org.bandrsoftwares.celestialdiary.model.dto.establishment.WrappedEstablishmentDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.Employee;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record EmployeeDTO(String id, String email, String firstName, String lastName, String comment, String photo, String gender,
                          String phone, boolean isTechnician, boolean activated, List<String> tags,
                          List<RoleDTO> roles,
                          Set<WrappedEstablishmentDTO> assignedEstablishments, String creationDate) {

    public EmployeeDTO(Employee employee) {
        this(employee.getId(),
             employee.getEmail(),
             employee.getFirstName(),
             employee.getLastName(),
             employee.getComment(),
             employee.getPhoto(),
             employee.getGender().name(),
             employee.getPhone(),
             employee.getIsTechnician(),
             employee.getActivated(),
             employee.getTags(),
             employee.getRoles().stream().map(RoleDTO::new).toList(),
             employee.getAssignedEstablishments().stream().map(WrappedEstablishmentDTO::new).collect(Collectors.toSet()),
             employee.getCreationDate().toString());
    }
}

