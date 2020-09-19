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

public class NoSpecialCharactersTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    class Model {
        @NoSpecialCharacters
        String text;

        Model(String text) {
            this.text = text;
        }
    }

    @Test
    void testNoSpecialCharacters() {
        Model model = new Model("Hello world");
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
        assertTrue(constraintViolations.isEmpty());
    }

    @Test
    void testSpecialCharactersOnly() {
        Model model = new Model("@#$");
        Set<ConstraintViolation<Model>> constraintViolations = validator.validate(model);
        assertFalse(constraintViolations.isEmpty());
    }
    
    @Test
    void testWithSpecialCharacters() {
        Model model = new Model("Hello world @");
        Set<ConstraintViolation<Model>> constraintViolations = validator.validate(model);
        assertFalse(constraintViolations.isEmpty());
    }

}
