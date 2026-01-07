package com.precisely.rapidcx.interview;

import com.precisely.rapidcx.interview.models.PriceChangeEntry;
import com.precisely.rapidcx.interview.models.PriceReportRequest;
import com.precisely.rapidcx.interview.models.PriceReportResponse;
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
        PriceReportRequest requestObject = new PriceReportRequest();
        requestObject.setYear(2024);
        requestObject.setNumberOfRecords(10);

        PriceReportResponse response = priceReportService.generatePriceReport(requestObject);

        assertThat(response).isNotNull();
        assertThat(response.getTopPriceIncreases()).hasSize(10);
        assertThat(response.getTopPriceDecreases()).hasSize(10);
        for (PriceChangeEntry entry : response.getTopPriceIncreases()) {
            assertThat(entry.getName()).isNotBlank();
            assertThat(entry.getPriceChange()).isGreaterThan(0);
        }
        for (PriceChangeEntry entry : response.getTopPriceDecreases()) {
            assertThat(entry.getName()).isNotBlank();
            assertThat(entry.getPriceChange()).isLessThan(0);
        }
        assertThat(response.getTopPriceIncreases()).isSortedAccordingTo(Comparator.comparingDouble(PriceChangeEntry::getPriceChange).reversed());
        assertThat(response.getTopPriceDecreases()).isSortedAccordingTo(Comparator.comparingDouble(PriceChangeEntry::getPriceChange));
    }
}
