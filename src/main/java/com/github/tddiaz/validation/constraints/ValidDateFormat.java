package com.github.tddiaz.validation.constraints;

import com.github.tddiaz.validation.DateFormatValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by tristandiaz on 10/31/17.
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = DateFormatValidator.class)
public @interface ValidDateFormat {

    String message() default "Invalid Date Format";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
