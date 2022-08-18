package org.bandrsoftwares.celestialdiary.model.dto.establishment;

import org.bandrsoftwares.celestialdiary.model.dto.general.time.DatedTimeIntervalListDTO;
import org.bandrsoftwares.celestialdiary.model.dto.general.time.NonDatedTimeIntervalListDTO;
import org.bandrsoftwares.celestialdiary.model.dto.person.employee.WrappedEmployeeDTO;
import org.bandrsoftwares.celestialdiary.model.general.Address;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.EstablishmentEquipment;

import java.util.List;
import java.util.Map;

public record EstablishmentDTO(String id, String name, String description,
                               Address address,
                               NonDatedTimeIntervalListDTO mondayOpening,
                               NonDatedTimeIntervalListDTO tuesdayOpening,
                               NonDatedTimeIntervalListDTO wednesdayOpening,
                               NonDatedTimeIntervalListDTO thursdayOpening,
                               NonDatedTimeIntervalListDTO fridayOpening,
                               NonDatedTimeIntervalListDTO saturdayOpening,
                               NonDatedTimeIntervalListDTO sundayOpening,
                               List<DatedTimeIntervalListDTO> exceptionalOpening,
                               List<DatedTimeIntervalListDTO> exceptionalClosing,
                               List<EstablishmentProductDTO> proposedProducts,
                               List<EstablishmentPrestationDTO> proposedServices,
                               List<EstablishmentBundleDTO> proposedBundles,
                               List<EstablishmentEquipmentContainerDTO> equipments,
                               List<WrappedEmployeeDTO> assignedEmployees,
                               boolean activated,
                               String creationDate) {

    public EstablishmentDTO(Establishment establishment) {
        this(establishment.getId(),
             establishment.getName(),
             establishment.getDescription(),
             establishment.getAddress(),
             establishment.getMondayOpening() != null ? new NonDatedTimeIntervalListDTO(establishment.getMondayOpening()) : null,
             establishment.getTuesdayOpening() != null ? new NonDatedTimeIntervalListDTO(establishment.getTuesdayOpening()) : null,
             establishment.getWednesdayOpening() != null ? new NonDatedTimeIntervalListDTO(establishment.getWednesdayOpening()) : null,
             establishment.getThursdayOpening() != null ? new NonDatedTimeIntervalListDTO(establishment.getThursdayOpening()) : null,
             establishment.getFridayOpening() != null ? new NonDatedTimeIntervalListDTO(establishment.getFridayOpening()) : null,
             establishment.getSaturdayOpening() != null ? new NonDatedTimeIntervalListDTO(establishment.getSaturdayOpening()) : null,
             establishment.getSundayOpening() != null ? new NonDatedTimeIntervalListDTO(establishment.getSundayOpening()) : null,
             establishment.getExceptionalOpening() != null ?
                     establishment.getExceptionalOpening().stream().map(DatedTimeIntervalListDTO::new).toList() : null,
             establishment.getExceptionalClosing() != null ?
                     establishment.getExceptionalClosing().stream().map(DatedTimeIntervalListDTO::new).toList() : null,
             establishment.getProposedProducts() != null ? establishment.getProposedProducts().stream().map(EstablishmentProductDTO::new).toList()
                     : null,
             establishment.getProposedPrestations() != null ?
                     establishment.getProposedPrestations().stream().map(EstablishmentPrestationDTO::new).toList() : null,
             establishment.getProposedBundles() != null ? establishment.getProposedBundles().stream().map(EstablishmentBundleDTO::new).toList() :
                     null,
             establishment.getEquipments() != null ? establishment.getEquipments().entrySet().stream().map(entry -> {
                 String equipmentId = entry.getKey();
                 Map<String, EstablishmentEquipment> mapEquipments = entry.getValue();
                 return new EstablishmentEquipmentContainerDTO(equipmentId, mapEquipments.values().stream().map(EstablishmentEquipmentDTO::new).toList());
             }).toList() : null,
             establishment.getAssignedEmployees() != null ? establishment.getAssignedEmployees().stream().map(WrappedEmployeeDTO::new).toList() :
                     null,
             establishment.getActivated(),
             establishment.getCreationDate().toString());
    }
}
