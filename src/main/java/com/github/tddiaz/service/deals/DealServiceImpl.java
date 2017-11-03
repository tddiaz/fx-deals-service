package com.github.tddiaz.service.deals;

import com.github.tddiaz.domain.InvalidDeal;
import com.github.tddiaz.domain.TransactionLog;
import com.github.tddiaz.domain.ValidDeal;
import com.github.tddiaz.repository.InvalidDealRepository;
import com.github.tddiaz.repository.ValidDealRepository;
import com.github.tddiaz.service.dto.DealDto;
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
    public List<InvalidDeal> saveInvalidDeals(List<InvalidDeal> invalidDeals) {
        return invalidDealRepository.save(invalidDeals);
    }

    @Override
    @Transactional
    public List<ValidDeal> saveValidDeals(List<ValidDeal> validDeals) {
        return validDealRepository.save(validDeals);
    }

    @Override
    @Transactional
    public void batchSave(List<DealDto> dealDtos, TransactionLog transactionLog) {

    }


    @Override
    public ValidDeal findValidDealByDealId(String dealId) {
        return validDealRepository.findByDealId(dealId);
    }
}
