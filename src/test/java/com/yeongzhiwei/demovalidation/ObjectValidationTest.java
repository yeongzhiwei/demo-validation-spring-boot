package com.yeongzhiwei.demovalidation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ObjectValidationTest {

    private static ObjectValidator objectValidator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        objectValidator = new ObjectValidator(validator);
    }

    class Model {
        @NotNull
        String text;

        Model(String text) {
            this.text = text;
        }
    }

    @Test
    void testValid() {
        Model model = new Model("Hello");
        String actual = objectValidator.validate(model);
        assertTrue(actual.isEmpty());
    }

    @Test
    void testInvalid() {
        Model model = new Model(null);
        String actual = objectValidator.validate(model);
        assertFalse(actual.isEmpty());
        assertThat(actual, containsString("text"));
        assertThat(actual, containsString("must not be null"));
    }
    
}
