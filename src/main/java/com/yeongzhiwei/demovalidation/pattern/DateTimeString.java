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
 * Asserts that the annotated {@code CharSequence} is formatted as a valid date/time pattern.
 * Default pattern is yyyyMMddHHmm. See {@code SimpleDateFormat} for patterns.
 * <p/>
 * Accepts {@code CharSequence}, {@code null} elements are considered valid.
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DateTimeValidator.class)
public @interface DateTimeString {

    @OverridesAttribute(constraint = Pattern.class, name="message")
    String message() default "must be formatted as a valid date/time pattern";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default { };

    String format() default "yyyyMMddHHmm";

    /**
     * Defines several {@code @DateTimeString} annotations on the same element.
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        DateTimeString[] value();
    }
}