package com.baranovskiy.webapp.validator;

import com.baranovskiy.webapp.validator.annotation.FloatValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FloatValidator implements ConstraintValidator<FloatValue, String> {

    private float min;

    private float max;

    private boolean isInRange(float price) {
        return (price >= min) && (price <= max);
    }

    @Override
    public void initialize(final FloatValue floatValue) {
        this.min = floatValue.min();
        this.max = floatValue.max();
    }

    @Override
    public boolean isValid(String price, ConstraintValidatorContext constraintValidatorContext) {
        return price != null && price.matches("\\d+|\\d+\\.\\d+") && isInRange(Float.parseFloat(price));
    }

}

