package com.github.tddiaz.controller;

import com.github.tddiaz.controller.dto.SummaryDto;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * Created by tristandiaz on 11/4/17.
 */
@Controller
@RequestMapping("/summary")
public class DealsImportedSummaryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DealsImportedSummaryController.class);

    private DealsImportedSummaryControllerHelper dealsImportedSummaryControllerHelper;

    @Autowired
    public void setDealsImportedSummaryControllerHelper(DealsImportedSummaryControllerHelper dealsImportedSummaryControllerHelper) {
        this.dealsImportedSummaryControllerHelper = dealsImportedSummaryControllerHelper;
    }

    @GetMapping
    public String getPage() {
        return "views/summary-page";
    }

    @GetMapping(value = "/search", consumes = "application/json", produces = "application/json")
    public ResponseEntity search(@RequestParam("fileName") String fileName) {

        if (StringUtils.isBlank(fileName)) {
            return ResponseEntity.badRequest().body("Please Enter a File name.");
        }

        LOGGER.info("Searching for deals summary by file name: {}", fileName);

        final SummaryDto summaryDto = dealsImportedSummaryControllerHelper.searchDealsSummary(fileName);

        if (Objects.isNull(summaryDto)) {
            return ResponseEntity.badRequest().body("No Records Found.");
        }

        return ResponseEntity.ok(summaryDto);
    }
}
