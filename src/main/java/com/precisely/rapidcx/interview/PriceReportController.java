package com.precisely.rapidcx.interview;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.precisely.rapidcx.interview.models.PriceReportResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.io.StringWriter;

@RestController
public class PriceReportController {
    private final PriceReportService priceReportService;
    private final ObjectMapper objectMapper;

    public PriceReportController(PriceReportService priceReportService, ObjectMapper objectMapper) {
        this.priceReportService = priceReportService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/")
    public ResponseEntity<String> generatePriceReport() {
        try {
            PriceReportResponse response = priceReportService.generatePriceReport();
            String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
            return ResponseEntity.ok()
                    .header("Content-Type", "application/json")
                    .body(prettyJson);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String errorDetails = "<pre>Exception: " + e.getClass().getName() + "\n"
                    + "Message: " + e.getMessage() + "\n"
                    + "Stack Trace:\n" + sw + "</pre>";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("Content-Type", "text/html")
                    .body(errorDetails);
        }
    }
}
