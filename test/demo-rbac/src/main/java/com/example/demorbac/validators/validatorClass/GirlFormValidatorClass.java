package com.example.demorbac.validators.validatorClass;

import com.example.demorbac.validators.GirlFormIdValidator;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GirlFormValidatorClass implements ConstraintValidator<GirlFormIdValidator, Object> {

    private String values;

    @Override
    public void initialize(GirlFormIdValidator girlFormValidator) {
        this.values = girlFormValidator.values();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
