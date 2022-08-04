package org.bandrsoftwares.celestialdiary.model.mongodb.person.employee;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.bandrsoftwares.celestialdiary.model.mongodb.person.Person;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.prestation.Prestation;
import org.bandrsoftwares.celestialdiary.security.privilege.Privilege;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "Employee")
public class Employee extends Person {

    private String password;

    private Boolean activated;

    private List<String> tags;

    @ToString.Exclude
    @DocumentReference(collection = "Role")
    private List<Role> roles;

    @ToString.Exclude
    @DocumentReference(collection = "Establishment")
    private List<Establishment> assignedEstablishments;

    @ToString.Exclude
    @DocumentReference(collection = "Prestation")
    private List<Prestation> praticablePrestations;

    // Constructors.

    @Builder
    public Employee(String id, String email, String firstName, String lastName, String comment, String photo,
                    PersonGender gender, String phone, Company company, Instant creationDate, String password, Boolean activated,
                    List<String> tags, List<Role> roles,
                    List<Establishment> assignedEstablishments,
                    List<Prestation> praticablePrestations) {
        super(id, email, firstName, lastName, comment, photo, gender, phone, company, creationDate);
        this.password = password;
        this.activated = activated;
        this.tags = tags;
        this.roles = roles;
        this.assignedEstablishments = assignedEstablishments;
        this.praticablePrestations = praticablePrestations;
    }


    // Methods.

    public List<String> allCompanyPrivilegeIdentifiers() {
        List<String> companyPrivileges = Lists.newArrayList();
        if (roles != null) {
            for (Role role : roles) {
                for (Privilege companyPrivilege : role.getCompanyPrivileges()) {
                    companyPrivileges.add(companyPrivilege.getIdentifierName());
                }
            }
        }

        return companyPrivileges;
    }

    public Map<String, List<String>> allEstablishmentPrivilegeIdentifiers() {
        Map<String, List<String>> establishmentPrivileges = Maps.newHashMap();

        if (roles != null) {
            for (Role role : roles) {
                for (EstablishmentRole establishmentRole : role.getEstablishmentRoles()) {
                    List<String> establishmentRoleIdentifiers = Lists.newArrayList();
                    establishmentPrivileges.put(establishmentRole.getEstablishment().getId(), establishmentRoleIdentifiers);
                    addEstablishmentPrivilege(establishmentRole, establishmentRoleIdentifiers);
                }
            }
        }

        return establishmentPrivileges;
    }

    private void addEstablishmentPrivilege(EstablishmentRole establishmentRole, List<String> establishmentRoleIdentifiers) {
        for (Privilege establishmentPrivilege : establishmentRole.getEstablishmentPrivileges()) {
            establishmentRoleIdentifiers.add(establishmentPrivilege.getIdentifierName());
        }
    }

    public boolean isAssignedTo(Establishment establishment) {
        return getAssignedEstablishments().stream().map(Establishment::getId).collect(Collectors.toSet()).contains(establishment.getId());
    }
}
