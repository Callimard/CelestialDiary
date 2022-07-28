package org.bandrsoftwares.celestialdiary.model.mongodb.employee;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.bandrsoftwares.celestialdiary.security.privilege.company.CompanyManagementPrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.establishment.EstablishmentManagementPrivilege;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.Instant;
import java.util.List;
import java.util.Map;
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

    public List<String> allCompanyPrivilegeIdentifiers() {
        List<String> companyPrivileges = Lists.newArrayList();
        if (roles != null) {
            for (Role role : roles) {
                for (Privilege companyPrivilege : role.getCompanyPrivileges()) {
                    Optional<CompanyManagementPrivilege> privilege = Privilege.extractCompanyPrivilege(companyPrivilege);
                    if (privilege.isPresent()) {
                        companyPrivileges.add(privilege.get().name());
                    } else {
                        log.error("Fail to extract CompanyManagementPrivilege of the identifier {}", companyPrivilege.getIdentifierName());
                    }
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
            Optional<EstablishmentManagementPrivilege> privilege = Privilege.extractEstablishmentPrivilege(establishmentPrivilege);
            if (privilege.isPresent()) {
                establishmentRoleIdentifiers.add(privilege.get().name());
            } else {
                log.error("Fail to extract EstablishmentManagementPrivilege of the identifier {}",
                          establishmentPrivilege.getIdentifierName());
            }
        }
    }

    public boolean isAssignedTo(Establishment establishment) {
        return getAssignedEstablishments().stream().map(Establishment::getId).collect(Collectors.toSet()).contains(establishment.getId());
    }
}
