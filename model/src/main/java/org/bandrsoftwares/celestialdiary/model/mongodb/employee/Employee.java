package org.bandrsoftwares.celestialdiary.model.mongodb.employee;

import com.google.common.collect.Sets;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.bandrsoftwares.celestialdiary.security.privilege.company.CompanyManagementPrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.establishment.EstablishmentPrivilege;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A User
 */
@Slf4j
@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Employee")
public class Employee {

    @Id
    private String id;

    private String email;
    private String password;

    private String firstName;
    private String lastName;

    private String comment;

    private String photo;

    private PersonGender gender;

    private String phone;

    private Boolean isTechnician;

    private Boolean activated;

    private List<String> tags;

    @ToString.Exclude
    @DocumentReference(collection = "Role")
    private List<Role> roles;

    @ToString.Exclude
    @DocumentReference(collection = "Establishment")
    private Set<Establishment> assignedEstablishments;

    @ToString.Exclude
    @DocumentReference(collection = "Company")
    private Company company;

    private Instant creationDate;

    // Methods.

    public Set<String> allAuthorities() {
        Set<String> authorities = Sets.newHashSet();
        if (roles != null)
            for (Role role : roles) {
                Company roleCompany = role.getCompany();
                addAuthorities(authorities, role, roleCompany, role.getEstablishment());
            }
        return authorities;
    }

    private void addAuthorities(Set<String> authorities, Role role, Company roleCompany, Establishment roleEstablishment) {
        for (Privilege privilege : role.getPrivileges()) {
            Optional<CompanyManagementPrivilege> opCompanyPrivilege = extractCompanyPrivilege(privilege);
            Optional<EstablishmentPrivilege> opEstablishmentPrivilege = extractEmployeePrivilege(privilege);

            if (opCompanyPrivilege.isPresent()) {
                authorities.add(opCompanyPrivilege.get().getPrivilegeFormatted(roleCompany.getId()));
            } else if (opEstablishmentPrivilege.isPresent()) {
                authorities.add(opEstablishmentPrivilege.get().getPrivilege(roleCompany.getId(), roleEstablishment.getId()));
            } else {
                log.error("No convertible privilege {} -> privilege ignored", privilege);
            }
        }
    }

    private Optional<CompanyManagementPrivilege> extractCompanyPrivilege(Privilege privilege) {
        try {
            return Optional.of(CompanyManagementPrivilege.valueOf(privilege.getIdentifierName()));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    private Optional<EstablishmentPrivilege> extractEmployeePrivilege(Privilege privilege) {
        try {
            return Optional.of(EstablishmentPrivilege.valueOf(privilege.getIdentifierName()));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public boolean isAssignedTo(Establishment establishment) {
        return getAssignedEstablishments().stream().map(Establishment::getId).collect(Collectors.toSet()).contains(establishment.getId());
    }
}
