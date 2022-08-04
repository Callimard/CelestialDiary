package org.bandrsoftwares.celestialdiary.model.dto.person.employee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bandrsoftwares.celestialdiary.model.dto.establishment.WrappedEstablishmentDTO;
import org.bandrsoftwares.celestialdiary.model.dto.person.PersonDTO;
import org.bandrsoftwares.celestialdiary.model.dto.saleable.prestation.PrestationDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.person.employee.Employee;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTO extends PersonDTO {

    private boolean activated;
    private List<String> tags;
    private List<RoleDTO> roles;
    private Set<WrappedEstablishmentDTO> assignedEstablishments;
    private List<PrestationDTO> praticablePrestations;

    public EmployeeDTO(Employee employee) {
        super(employee);

        this.activated = employee.getActivated();
        this.tags = employee.getTags();
        this.roles = employee.getRoles().stream().map(RoleDTO::new).toList();
        this.assignedEstablishments = employee.getAssignedEstablishments().stream().map(WrappedEstablishmentDTO::new).collect(Collectors.toSet());
        this.praticablePrestations = employee.getPraticablePrestations().stream().map(PrestationDTO::new).toList();
    }
}

