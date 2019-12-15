package com.coding.coupon.common.validation.validator;

import com.coding.coupon.common.DictDefinition;
import com.coding.coupon.common.validation.constraints.EnumValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class EnumValueValidator implements ConstraintValidator<EnumValue, Object> {

    private String[] strValues;
    private int[] intValues;
    private Class<? extends DictDefinition.BaseEnum> enumClazz;

    @Override
    public void initialize(EnumValue constraintAnnotation) {
        strValues = constraintAnnotation.strValues();
        intValues = constraintAnnotation.intValues();
        enumClazz = constraintAnnotation.enumClazz();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        // 即使枚举又是 BaseEnum 的子类
        if (DictDefinition.BaseEnum.class.isAssignableFrom(enumClazz)
                && Enum.class.isAssignableFrom(enumClazz)) {
            return DictDefinition.getByValue(enumClazz, value) != null;
        } else {
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
        }
        return false;
    }
}
