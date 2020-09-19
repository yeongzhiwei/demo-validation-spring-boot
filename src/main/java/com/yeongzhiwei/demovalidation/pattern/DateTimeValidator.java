package com.yeongzhiwei.demovalidation.pattern;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateTimeValidator implements ConstraintValidator<DateTimeString, String> {

    private String format;

    @Override
    public void initialize(DateTimeString constraintAnnotation) {
        this.format = constraintAnnotation.format();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintContext) {
        if (value == null) {
            return true;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setLenient(false);
        try {
            simpleDateFormat.parse(value);
        } catch (ParseException ex) {
            return false;
        }

        return true;
    }
}
