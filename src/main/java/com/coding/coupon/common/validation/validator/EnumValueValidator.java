package com.coding.coupon.common.validation.validator;

import com.coding.coupon.common.validation.constraints.EnumValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class EnumValueValidator implements ConstraintValidator<EnumValue, Object> {

    private String[] strValues;
    private int[] intValues;

    @Override
    public void initialize(EnumValue constraintAnnotation) {
        strValues = constraintAnnotation.strValues();
        intValues = constraintAnnotation.intValues();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value instanceof String) {
            for (String strValue : strValues) {
                if (strValue.equals(value)) {
                    return true;
                }
            }
        } else if (value instanceof Integer) {
            for (Integer intValue : intValues) {
                if (Objects.equals(intValue, value)) {
                    return true;
                }
            }
        }
        return false;
    }
}
