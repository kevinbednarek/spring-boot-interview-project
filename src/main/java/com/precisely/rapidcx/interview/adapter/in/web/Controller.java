package com.precisely.rapidcx.interview.adapter.in.web;

import com.precisely.rapidcx.interview.api.InterviewProjectApi;
import com.precisely.rapidcx.interview.application.port.in.PriceReportUseCase;
import com.precisely.rapidcx.interview.model.RequestObject;
import com.precisely.rapidcx.interview.model.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class Controller implements InterviewProjectApi {
    private final PriceReportUseCase priceReportUseCase;

    @Autowired
    public Controller(PriceReportUseCase priceReportUseCase) {
        this.priceReportUseCase = priceReportUseCase;
    }

    @Override
    public ResponseEntity<ResponseObject> generatePriceReport(RequestObject requestObject) {
        ResponseObject responseObject = priceReportUseCase.generatePriceReport(requestObject);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
