package org.bandrsoftwares.celestialdiary.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyCoherenceException;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyCoherenceVerificationException;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.employee.EmployeeId;
import org.bandrsoftwares.celestialdiary.aop.establishment.EstablishmentId;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.Employee;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;

@Slf4j
@Aspect
@Component
@Order(100)
public class CheckAspect {

    @Before("@annotation(org.bandrsoftwares.celestialdiary.aop.company.CheckCompanyCoherence)")
    void verifyCompanyCoherence(JoinPoint jp) {
        log.info("In check company coherence");
        String companyId = AopTool.extractSpecificParameter(jp, CompanyId.class);

        Parameter[] parameters = AopTool.extractJoinPointMethodParameter(jp);
        for (Parameter parameter : parameters) {
            if (parameter.isAnnotationPresent(EmployeeId.class)) {
                checkEmployeeCoherence(companyId);
            } else if (parameter.isAnnotationPresent(EstablishmentId.class)) {
                checkEstablishmentCoherence(companyId);
            }
        }
    }

    private void checkEmployeeCoherence(String companyId) {
        Employee employee = SearchingAspect.EMPLOYEE_FOUND.get();
        if (employee != null) {
            if (!employee.getCompanySummary().getCompany().getId().equals(companyId)) {
                throw new CompanyCoherenceException(
                        "The employee with the id " + employee.getId() + " is not in the company with the id " + companyId);
            }
        } else {
            throw new CompanyCoherenceVerificationException("The annotation @CheckCompanyCoherence must be use in association with " +
                                                                    "annotations @SearchEmployee and/or @SearchEstablishment");
        }
    }

    private void checkEstablishmentCoherence(String companyId) {
        Establishment establishment = SearchingAspect.ESTABLISHMENT_FOUND.get();
        if (establishment != null) {
            if (!establishment.getCompany().getId().equals(companyId)) {
                throw new CompanyCoherenceException(
                        "The establishment with the id " + establishment.getId() + " is not in the company with the id " + companyId);
            }
        } else {
            throw new CompanyCoherenceVerificationException("The annotation @CheckCompanyCoherence must be use in association with " +
                                                                    "annotations @SearchEmployee and/or @SearchEstablishment");
        }
    }
}
