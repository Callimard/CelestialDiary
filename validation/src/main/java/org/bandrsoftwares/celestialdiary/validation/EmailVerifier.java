package org.bandrsoftwares.celestialdiary.validation;

import org.apache.commons.validator.routines.EmailValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailVerifier implements ConstraintValidator<ValidEmail, String> {
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email == null || EmailValidator.getInstance().isValid(email);
    }
}
