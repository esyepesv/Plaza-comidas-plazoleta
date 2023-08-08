package com.reto.plazoleta.application.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Documented
@Constraint(validatedBy = NotNumericOnlyValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNumericOnly {
    String message() default "El nombre no puede consistir únicamente en números";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

