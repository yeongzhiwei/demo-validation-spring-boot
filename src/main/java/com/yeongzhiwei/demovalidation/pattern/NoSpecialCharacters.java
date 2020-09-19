package com.yeongzhiwei.demovalidation.pattern;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

/**
 * Asserts that the annotated {@code CharSequence} does not contain special characters.
 * Special characters are ; ' , ! @ # $ % & * ( ) + = | < > ? { } [ ].'
 * <p/>
 * Accepts {@code CharSequence}, {@code null} elements are considered valid.
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {})
@Pattern(regexp = "^[^;',!@#$%&*()+=|<>?{}\\[\\]~]*$")
public @interface NoSpecialCharacters {

    @OverridesAttribute(constraint = Pattern.class, name="message")
    String message() default "must not contain special characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default { };

    /**
     * Defines several {@code @NoSpecialCharacters} annotations on the same element.
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        NoSpecialCharacters[] value();
    }
}