package com.precisely.rapidcx.interview.application.service;

import com.precisely.rapidcx.interview.model.DrugEntry;
import com.precisely.rapidcx.interview.model.RequestObject;
import com.precisely.rapidcx.interview.model.ResponseObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PriceReportServiceTest {
    @Autowired
    private PriceReportService priceReportService;

    @Test
    void generatePriceReportTest() {
        RequestObject requestObject = new RequestObject();
        requestObject.setYear(2024);
        requestObject.setNumberOfRecords(10);

        ResponseObject response = priceReportService.generatePriceReport(requestObject);

        assertThat(response).isNotNull();
        assertThat(response.getTopPriceIncreases()).hasSize(10);
        assertThat(response.getTopPriceDecreases()).hasSize(10);
        for (DrugEntry entry : response.getTopPriceIncreases()) {
            assertThat(entry.getName()).isNotBlank();
            assertThat(entry.getPriceChange()).isGreaterThan(0);
        }
        for (DrugEntry entry : response.getTopPriceDecreases()) {
            assertThat(entry.getName()).isNotBlank();
            assertThat(entry.getPriceChange()).isLessThan(0);
        }
        assertThat(response.getTopPriceIncreases()).isSortedAccordingTo(Comparator.comparingDouble(DrugEntry::getPriceChange).reversed());
        assertThat(response.getTopPriceDecreases()).isSortedAccordingTo(Comparator.comparingDouble(DrugEntry::getPriceChange));
    }
}
