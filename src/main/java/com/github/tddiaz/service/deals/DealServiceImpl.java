package com.github.tddiaz.service.deals;

import com.github.tddiaz.domain.InvalidDeal;
import com.github.tddiaz.domain.ValidDeal;
import com.github.tddiaz.repository.InvalidDealRepository;
import com.github.tddiaz.repository.ValidDealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tristandiaz on 10/29/17.
 */
@Service("dealService")
public class DealServiceImpl implements DealService {

    private InvalidDealRepository invalidDealRepository;

    private ValidDealRepository validDealRepository;

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
    public void saveInvalidDeals(List<InvalidDeal> invalidDeals) {
        invalidDealRepository.batchSave(invalidDeals);
    }

    @Override
    @Transactional
    public void saveValidDeals(List<ValidDeal> validDeals) {
         validDealRepository.batchSave(validDeals);
    }


    @Override
    public ValidDeal findValidDealByDealId(String dealId) {
        return validDealRepository.findByDealId(dealId);
    }
}
