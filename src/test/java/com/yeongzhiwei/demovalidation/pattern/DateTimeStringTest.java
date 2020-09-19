package com.yeongzhiwei.demovalidation.pattern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DateTimeStringTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    class Model {
        @DateTimeString
        String text;

        Model(String text) {
            this.text = text;
        }
    }

    @Test
    void testValidDatetime() {
        Model model = new Model("202009301230");
        Set<ConstraintViolation<Model>> constraintViolations = validator.validate(model);
        assertTrue(constraintViolations.isEmpty());
    }

    @Test
    void testNull() {
        Model model = new Model(null);
        Set<ConstraintViolation<Model>> constraintViolations = validator.validate(model);
        assertTrue(constraintViolations.isEmpty());
    }

    @Test
    void testEmpty() {
        Model model = new Model("");
        Set<ConstraintViolation<Model>> constraintViolations = validator.validate(model);
        assertFalse(constraintViolations.isEmpty());
    }

    @Test
    void testInvalidFormat() {
        Model model = new Model("20200930");
        Set<ConstraintViolation<Model>> constraintViolations = validator.validate(model);
        assertFalse(constraintViolations.isEmpty());
    }

    @Test
    void testInvalidYear() {
        Model model = new Model("202@09301230");
        Set<ConstraintViolation<Model>> constraintViolations = validator.validate(model);
        assertFalse(constraintViolations.isEmpty());
    }
    
    @Test
    void testInvalidMonth() {
        Model model = new Model("202013301230");
        Set<ConstraintViolation<Model>> constraintViolations = validator.validate(model);
        assertFalse(constraintViolations.isEmpty());
    }

    @Test
    void testInvalidDay() {
        Model model = new Model("202009311230");
        Set<ConstraintViolation<Model>> constraintViolations = validator.validate(model);
        assertFalse(constraintViolations.isEmpty());
    }

    @Test
    void testInvalidHour() {
        Model model = new Model("202009302530");
        Set<ConstraintViolation<Model>> constraintViolations = validator.validate(model);
        assertFalse(constraintViolations.isEmpty());
    }

    @Test
    void testInvalidMinute() {
        Model model = new Model("202009301261");
        Set<ConstraintViolation<Model>> constraintViolations = validator.validate(model);
        assertFalse(constraintViolations.isEmpty());
    }

}
