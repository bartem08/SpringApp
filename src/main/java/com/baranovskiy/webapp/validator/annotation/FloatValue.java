package com.baranovskiy.webapp.validator.annotation;

import com.baranovskiy.webapp.validator.FloatValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FloatValidator.class)
public @interface FloatValue {

    String message() default "Must be not null number. Example: 2.5, 5, 5.0";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    float min() default Float.MIN_VALUE;
    float max() default Float.MAX_VALUE;

}
