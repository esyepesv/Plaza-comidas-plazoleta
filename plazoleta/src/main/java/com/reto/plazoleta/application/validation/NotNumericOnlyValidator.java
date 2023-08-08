package com.reto.plazoleta.application.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNumericOnlyValidator implements ConstraintValidator<NotNumericOnly, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !value.matches("\\d+") || value.matches(".*[a-zA-Z].*");
    }
}

