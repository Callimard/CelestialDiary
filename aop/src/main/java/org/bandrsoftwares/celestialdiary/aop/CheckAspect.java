package org.bandrsoftwares.celestialdiary.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyCoherenceException;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyCoherenceVerificationException;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.employee.EmployeeId;
import org.bandrsoftwares.celestialdiary.aop.employee.RoleId;
import org.bandrsoftwares.celestialdiary.aop.establishment.EstablishmentId;
import org.bandrsoftwares.celestialdiary.aop.saleable.bundle.BundleId;
import org.bandrsoftwares.celestialdiary.aop.saleable.product.ProductId;
import org.bandrsoftwares.celestialdiary.aop.saleable.service.PrestationId;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.Employee;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.Role;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.bundle.Bundle;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.prestation.Prestation;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.product.Product;
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
        String companyId = AopTool.extractSpecificParameter(jp, CompanyId.class);

        Parameter[] parameters = AopTool.extractJoinPointMethodParameter(jp);
        for (Parameter parameter : parameters) {
            if (parameter.isAnnotationPresent(EmployeeId.class)) {
                checkEmployeeCoherence(companyId);
            } else if (parameter.isAnnotationPresent(EstablishmentId.class)) {
                checkEstablishmentCoherence(companyId);
            } else if (parameter.isAnnotationPresent(ProductId.class)) {
                checkProductCoherence(companyId);
            } else if (parameter.isAnnotationPresent(PrestationId.class)) {
                checkPrestationCoherence(companyId);
            } else if (parameter.isAnnotationPresent(BundleId.class)) {
                checkBundleCoherence(companyId);
            } else if (parameter.isAnnotationPresent(RoleId.class)) {
                checkRoleCoherence(companyId);
            }
        }
    }

    private void checkEmployeeCoherence(String companyId) {
        Employee employee = SearchingAspect.EMPLOYEE_FOUND.get();
        if (employee != null) {
            if (!employee.getCompany().getId().equals(companyId)) {
                throw new CompanyCoherenceException(
                        "The employee with the id " + employee.getId() + " is not in the company with the id " + companyId);
            }
        } else {
            throw new CompanyCoherenceVerificationException("The annotation @CheckCompanyCoherence must be use in association with " +
                                                                    "@Search annotation");
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
                                                                    "@Search annotation");
        }
    }

    private void checkProductCoherence(String companyId) {
        Product product = SearchingAspect.PRODUCT_FOUND.get();
        if (product != null) {
            if (!product.getCompany().getId().equals(companyId)) {
                throw new CompanyCoherenceException(
                        "The product with the id " + product.getId() + " is not in the company with the id " + companyId);
            }
        } else {
            throw new CompanyCoherenceVerificationException("The annotation @CheckCompanyCoherence must be use in association with " +
                                                                    "@Search annotation");
        }
    }

    private void checkPrestationCoherence(String companyId) {
        Prestation prestation = SearchingAspect.PRESTATION_FOUND.get();
        if (prestation != null) {
            if (!prestation.getCompany().getId().equals(companyId)) {
                throw new CompanyCoherenceException(
                        "The service with the id " + prestation.getId() + " is not in the company with the id " + companyId);
            }
        } else {
            throw new CompanyCoherenceVerificationException("The annotation @CheckCompanyCoherence must be use in association with " +
                                                                    "@Search annotation");
        }
    }

    private void checkBundleCoherence(String companyId) {
        Bundle bundle = SearchingAspect.BUNDLE_FOUND.get();
        if (bundle != null) {
            if (!bundle.getCompany().getId().equals(companyId)) {
                throw new CompanyCoherenceException(
                        "The bundle with the id " + bundle.getId() + " is not in the company with the id " + companyId);
            }
        } else {
            throw new CompanyCoherenceVerificationException("The annotation @CheckCompanyCoherence must be use in association with " +
                                                                    "@Search annotation");
        }
    }

    private void checkRoleCoherence(String companyId) {
        Role role = SearchingAspect.ROLE_FOUND.get();
        if (role != null) {
            if (!role.getCompany().getId().equals(companyId)) {
                throw new CompanyCoherenceException(
                        "The role with the id " + role.getId() + " is not in the company with the id " + companyId);
            }
        } else {
            throw new CompanyCoherenceVerificationException("The annotation @CheckCompanyCoherence must be use in association with " +
                                                                    "@Search annotation");
        }
    }
}
