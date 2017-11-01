package com.github.tddiaz.validation;

import com.github.tddiaz.AppContextProvider;
import com.github.tddiaz.service.deals.DealService;
import com.github.tddiaz.validation.constraints.UniqueDealId;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * Created by tristandiaz on 10/30/17.
 */
public class DealIdUniqueValidator implements ConstraintValidator<UniqueDealId, String> {

    private DealService dealService;

    public DealIdUniqueValidator() {
        dealService = AppContextProvider.getContext().getBean("dealService", DealService.class);
    }

    @Override
    public void initialize(UniqueDealId constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (StringUtils.isBlank(value)) {
            return true;
        }

        return Objects.isNull(dealService.findValidDealByDealId(value));
    }
}
