package com.precisely.rapidcx.interview;

import com.precisely.rapidcx.interview.models.PriceReportRequest;
import com.precisely.rapidcx.interview.models.PriceReportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceReportController {
    private final PriceReportService priceReportService;

    @Autowired
    public PriceReportController(PriceReportService priceReportService) {
        this.priceReportService = priceReportService;
    }

    @PostMapping("/interview-project/v1")
    public ResponseEntity<PriceReportResponse> generatePriceReport(@RequestBody PriceReportRequest request) {
        PriceReportResponse response = priceReportService.generatePriceReport(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
