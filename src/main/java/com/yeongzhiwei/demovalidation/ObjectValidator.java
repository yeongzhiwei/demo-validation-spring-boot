package com.yeongzhiwei.demovalidation;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectValidator {

    private Validator validator;

    public ObjectValidator(@Autowired Validator validator) {
        this.validator = validator;
    }

    public <T> String validate(T object) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);
        if (constraintViolations.size() > 0) {
            String error = constraintViolations.stream()
                .map(this::getErrorMessage)
                .collect(Collectors.joining(", "));
            return error;
        }
        return "";
    }
    
    private <T> String getErrorMessage(ConstraintViolation<T> violation) {
        return violation.getPropertyPath() + " (" + violation.getInvalidValue() + ") " + violation.getMessage();
    }

}