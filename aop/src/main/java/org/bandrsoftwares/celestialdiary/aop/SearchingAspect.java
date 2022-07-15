package org.bandrsoftwares.celestialdiary.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyId;
import org.bandrsoftwares.celestialdiary.aop.company.CompanyNotFoundException;
import org.bandrsoftwares.celestialdiary.aop.employee.EmployeeId;
import org.bandrsoftwares.celestialdiary.aop.employee.EmployeeNotFoundException;
import org.bandrsoftwares.celestialdiary.aop.establishment.EstablishmentId;
import org.bandrsoftwares.celestialdiary.aop.establishment.EstablishmentNotFoundException;
import org.bandrsoftwares.celestialdiary.aop.saleable.bundle.BundleId;
import org.bandrsoftwares.celestialdiary.aop.saleable.bundle.BundleNotFoundException;
import org.bandrsoftwares.celestialdiary.aop.saleable.product.ProductId;
import org.bandrsoftwares.celestialdiary.aop.saleable.product.ProductNotFoundException;
import org.bandrsoftwares.celestialdiary.aop.saleable.service.PrestationId;
import org.bandrsoftwares.celestialdiary.aop.saleable.service.ServiceNotFoundException;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.Company;
import org.bandrsoftwares.celestialdiary.model.mongodb.company.CompanyRepository;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.Employee;
import org.bandrsoftwares.celestialdiary.model.mongodb.employee.EmployeeRepository;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.Establishment;
import org.bandrsoftwares.celestialdiary.model.mongodb.establishment.EstablishmentRepository;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.bundle.Bundle;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.bundle.BundleRepository;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.product.Product;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.product.ProductRepository;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.prestation.Prestation;
import org.bandrsoftwares.celestialdiary.model.mongodb.saleable.prestation.PrestationRepository;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Aspect
@Component
@Order(0)
public class SearchingAspect {

    public static final ThreadLocal<Company> COMPANY_FOUND = new ThreadLocal<>();
    public static final ThreadLocal<Employee> EMPLOYEE_FOUND = new ThreadLocal<>();
    public static final ThreadLocal<Establishment> ESTABLISHMENT_FOUND = new ThreadLocal<>();
    public static final ThreadLocal<Product> PRODUCT_FOUND = new ThreadLocal<>();
    public static final ThreadLocal<Prestation> SERVICE_FOUND = new ThreadLocal<>();
    public static final ThreadLocal<Bundle> BUNDLE_FOUND = new ThreadLocal<>();

    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;
    private final EstablishmentRepository establishmentRepository;
    private final ProductRepository productRepository;
    private final PrestationRepository prestationRepository;
    private final BundleRepository bundleRepository;

    @Before("@annotation(org.bandrsoftwares.celestialdiary.aop.company.SearchCompany)")
    void searchAndShareCompany(JoinPoint jp) {
        String companyId = AopTool.extractSpecificParameter(jp, CompanyId.class);
        searchAndShareCompany(companyId);
    }

    private void searchAndShareCompany(String companyId) {
        Optional<Company> opCompany = companyRepository.findById(companyId);
        if (opCompany.isEmpty()) {
            throw new CompanyNotFoundException(companyId);
        } else {
            COMPANY_FOUND.set(opCompany.get());
        }
    }

    @Before("@annotation(org.bandrsoftwares.celestialdiary.aop.employee.SearchEmployee)")
    void searchAndShareEmployee(JoinPoint jp) {
        String employeeId = AopTool.extractSpecificParameter(jp, EmployeeId.class);
        searchAndShareEmployee(employeeId);
    }

    private void searchAndShareEmployee(String employeeId) {
        Optional<Employee> opEmployee = employeeRepository.findById(employeeId);
        if (opEmployee.isEmpty()) {
            throw new EmployeeNotFoundException(employeeId);
        } else {
            EMPLOYEE_FOUND.set(opEmployee.get());
        }
    }

    @Before("@annotation(org.bandrsoftwares.celestialdiary.aop.establishment.SearchEstablishment)")
    void searchAndShareEstablishment(JoinPoint jp) {
        String establishmentId = AopTool.extractSpecificParameter(jp, EstablishmentId.class);
        searchAndShareEstablishment(establishmentId);
    }

    private void searchAndShareEstablishment(String establishmentId) {
        Optional<Establishment> opEstablishment = establishmentRepository.findById(establishmentId);
        if (opEstablishment.isEmpty()) {
            throw new EstablishmentNotFoundException(establishmentId);
        } else {
            ESTABLISHMENT_FOUND.set(opEstablishment.get());
        }
    }

    @Before("@annotation(org.bandrsoftwares.celestialdiary.aop.saleable.product.SearchProduct)")
    void searchAndShareProduct(JoinPoint jp) {
        String productId = AopTool.extractSpecificParameter(jp, ProductId.class);
        searchAndShareProduct(productId);
    }

    private void searchAndShareProduct(String productId) {
        Optional<Product> opProduct = productRepository.findById(productId);
        if (opProduct.isEmpty()) {
            throw new ProductNotFoundException(productId);
        } else {
            PRODUCT_FOUND.set(opProduct.get());
        }
    }

    @Before("@annotation(org.bandrsoftwares.celestialdiary.aop.saleable.service.SearchService)")
    void searchAndShareService(JoinPoint jp) {
        String serviceId = AopTool.extractSpecificParameter(jp, PrestationId.class);
        searchAndShareService(serviceId);
    }

    private void searchAndShareService(String serviceId) {
        Optional<Prestation> opService = prestationRepository.findById(serviceId);
        if (opService.isEmpty()) {
            throw new ServiceNotFoundException(serviceId);
        } else {
            SERVICE_FOUND.set(opService.get());
        }
    }

    @Before("@annotation(org.bandrsoftwares.celestialdiary.aop.saleable.bundle.SearchBundle)")
    void searchAndShareBundle(JoinPoint jp) {
        String bundleId = AopTool.extractSpecificParameter(jp, BundleId.class);
        searchAndShareBundle(bundleId);
    }

    private void searchAndShareBundle(String bundleId) {
        Optional<Bundle> opBundle = bundleRepository.findById(bundleId);
        if (opBundle.isEmpty()) {
            throw new BundleNotFoundException(bundleId);
        } else {
            BUNDLE_FOUND.set(opBundle.get());
        }
    }
}
