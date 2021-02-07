package biz.uoray.api.common.validation.validator;

import biz.uoray.api.common.validation.PositiveDouble;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PositiveDoubleValidator implements ConstraintValidator<PositiveDouble, Double> {

    @Override
    public void initialize(PositiveDouble constraintAnnotation) {
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return isPositiveDouble(value);
    }

    private boolean isPositiveDouble(Double value) throws IllegalArgumentException {
        return 0.0 <= value;
    }
}
