package com.srh.medicalmanagementsystem.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DOBValidator.class)
@Documented

public @interface PastOrPresentDate {

    String message() default "Date of birth must be today or in the past";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
