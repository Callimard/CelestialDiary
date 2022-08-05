package org.bandrsoftwares.celestialdiary.model.mongodb.person.employee;

import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeWorkingHoursRepository extends MongoRepository<EmployeeWorkingHours, String> {

    EmployeeWorkingHours findByEmployeeAndEstablishmentAndYearAndWeekNumber(Employee employee, Establishment establishment, int year,
                                                                              int weekNumber);
}
