package com.github.tddiaz.service.deals;


import com.github.tddiaz.domain.TransactionLog;
import com.github.tddiaz.domain.ValidDeal;
import com.github.tddiaz.service.dto.DealDto;

import java.util.List;

/**
 * Created by tristandiaz on 10/29/17.
 */
public interface DealService {

    void batchSave(List<DealDto> dealDtos, TransactionLog transactionLog);

    ValidDeal findValidDealByDealId(String dealId);

}
