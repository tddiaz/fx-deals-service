package com.github.tddiaz.validation.constraints;

import com.github.tddiaz.validation.CSVFileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by tristandiaz on 11/1/17.
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CSVFileValidator.class)
public @interface CSV {

    String message() default "Invalid File Format.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
