package com.precisely.rapidcx.interview.application.port.in;

import com.precisely.rapidcx.interview.model.RequestObject;
import com.precisely.rapidcx.interview.model.ResponseObject;

public interface PriceReportUseCase {
    ResponseObject generatePriceReport(RequestObject requestObject);
}
