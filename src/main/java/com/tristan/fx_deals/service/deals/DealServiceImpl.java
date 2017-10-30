package com.tristan.fx_deals.service.deals;

import com.tristan.fx_deals.domain.InvalidDeal;
import com.tristan.fx_deals.domain.ValidDeal;
import com.tristan.fx_deals.repository.InvalidDealRepository;
import com.tristan.fx_deals.repository.ValidDealRepository;
import com.tristan.fx_deals.service.dto.DealDto;
import com.tristan.fx_deals.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tristandiaz on 10/29/17.
 */
@Service("dealService")
public class DealServiceImpl implements DealService {

    private InvalidDealRepository invalidDealRepository;

    private ValidDealRepository validDealRepository;

    private DealsValidator dealsValidator;

    @Autowired
    public void setDealsValidator(DealsValidator dealsValidator) {
        this.dealsValidator = dealsValidator;
    }

    @Autowired
    public void setInvalidDealRepository(InvalidDealRepository invalidDealRepository) {
        this.invalidDealRepository = invalidDealRepository;
    }

    @Autowired
    public void setValidDealRepository(ValidDealRepository validDealRepository) {
        this.validDealRepository = validDealRepository;
    }

    @Override
    @Transactional
    public void batchSave(List<DealDto> dealDtos) {

        final List<ValidDeal> validDeals = new ArrayList<>(dealDtos.size());
        final List<InvalidDeal> invalidDeals = new ArrayList<>();

        for (DealDto dealDto : dealDtos) {
            if (dealsValidator.valid(dealDto)) {

                final ValidDeal validDeal = new ValidDeal();
                validDeal.setDealId(dealDto.getDealId());
                validDeal.setFromCurrency(dealDto.getFromCurrency());
                validDeal.setFromCurrency(dealDto.getToCurrency());
                validDeal.setFileName(dealDto.getFileName());
                validDeal.setAmount(dealDto.getAmount());

                validDeals.add(validDeal);

            } else {

                final InvalidDeal invalidDeal = new InvalidDeal();
                invalidDeal.setData(JsonUtil.toJsonString(dealDto));
                invalidDeal.setFileName(dealDto.getFileName());

                invalidDeals.add(invalidDeal);
            }
        }

        validDealRepository.save(validDeals);
        invalidDealRepository.save(invalidDeals);
    }
}
