package com.github.tddiaz.controller;

import com.github.tddiaz.controller.dto.SummaryDto;
import com.github.tddiaz.domain.TransactionLog;
import com.github.tddiaz.service.logging.TransactionLogService;
import com.github.tddiaz.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by tristandiaz on 11/4/17.
 */
@Component("dealsImportedSummaryControllerHelper")
public class DealsImportedSummaryControllerHelperImpl implements DealsImportedSummaryControllerHelper {

    private TransactionLogService transactionLogService;

    @Autowired
    public void setTransactionLogService(TransactionLogService transactionLogService) {
        this.transactionLogService = transactionLogService;
    }

    @Override
    public SummaryDto searchDealsSummary(String fileName) {

        final TransactionLog transactionLog = transactionLogService.findByFileName(fileName);

        if (Objects.isNull(transactionLog)) {
            return null;
        }

        final SummaryDto summaryDto = new SummaryDto();
        summaryDto.setProcessTime(String.valueOf(transactionLog.getProcessDuration()) + "s");
        summaryDto.setValidDeals(String.valueOf(transactionLog.getDealsImportedCount()));
        summaryDto.setInvalidDeals(String.valueOf(transactionLog.getInvalidDealsImportedCount()));
        summaryDto.setDatetime(DateTimeUtil.formatDateTime(transactionLog.getDateTime()));
        summaryDto.setFilename(transactionLog.getFileName());

        return summaryDto;
    }
}
