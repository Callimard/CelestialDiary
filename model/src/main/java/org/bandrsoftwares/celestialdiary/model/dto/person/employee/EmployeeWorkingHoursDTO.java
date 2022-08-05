package org.bandrsoftwares.celestialdiary.model.dto.person.employee;

import org.bandrsoftwares.celestialdiary.model.dto.establishment.WrappedEstablishmentDTO;
import org.bandrsoftwares.celestialdiary.model.dto.general.time.NonDatedTimeIntervalListDTO;
import org.bandrsoftwares.celestialdiary.model.mongodb.person.employee.EmployeeWorkingHours;

public record EmployeeWorkingHoursDTO(
        String id,
        int year,
        int weekNumber,
        NonDatedTimeIntervalListDTO monday,
        NonDatedTimeIntervalListDTO tuesday,
        NonDatedTimeIntervalListDTO wednesday,
        NonDatedTimeIntervalListDTO thursday,
        NonDatedTimeIntervalListDTO friday,
        NonDatedTimeIntervalListDTO saturday,
        NonDatedTimeIntervalListDTO sunday,
        WrappedEmployeeDTO employeeDTO,
        WrappedEstablishmentDTO establishmentDTO
) {

    public EmployeeWorkingHoursDTO(EmployeeWorkingHours employeeWorkingHours) {
        this(
                employeeWorkingHours.getId(),
                employeeWorkingHours.getYear(),
                employeeWorkingHours.getWeekNumber(),
                employeeWorkingHours.getMonday() != null ? new NonDatedTimeIntervalListDTO(employeeWorkingHours.getMonday()) : null,
                employeeWorkingHours.getTuesday() != null ? new NonDatedTimeIntervalListDTO(employeeWorkingHours.getTuesday()) : null,
                employeeWorkingHours.getWednesday() != null ? new NonDatedTimeIntervalListDTO(employeeWorkingHours.getWednesday()) : null,
                employeeWorkingHours.getThursday() != null ? new NonDatedTimeIntervalListDTO(employeeWorkingHours.getThursday()) : null,
                employeeWorkingHours.getFriday() != null ? new NonDatedTimeIntervalListDTO(employeeWorkingHours.getFriday()) : null,
                employeeWorkingHours.getSaturday() != null ? new NonDatedTimeIntervalListDTO(employeeWorkingHours.getSaturday()) : null,
                employeeWorkingHours.getSunday() != null ? new NonDatedTimeIntervalListDTO(employeeWorkingHours.getSunday()) : null,
                new WrappedEmployeeDTO(employeeWorkingHours.getEmployee()),
                new WrappedEstablishmentDTO(employeeWorkingHours.getEstablishment())
        );
    }
}
