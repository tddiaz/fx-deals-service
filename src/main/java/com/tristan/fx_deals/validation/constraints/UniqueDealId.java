package com.tristan.fx_deals.validation.constraints;

import com.tristan.fx_deals.validation.DealIdUniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by tristandiaz on 10/30/17.
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = DealIdUniqueValidator.class)
public @interface UniqueDealId {

    String message() default "Deal Id already exists";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
