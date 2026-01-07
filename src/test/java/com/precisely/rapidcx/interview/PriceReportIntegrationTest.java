package com.precisely.rapidcx.interview;

import com.precisely.rapidcx.interview.models.PriceChangeEntry;
import com.precisely.rapidcx.interview.models.PriceReportRequest;
import com.precisely.rapidcx.interview.models.PriceReportResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceReportIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void sampleRequest() {
        String url = "http://localhost:" + port + "/interview-project/v1";
        int year = 2024;
        int numberOfRecords = 10;

        PriceReportRequest request = PriceReportRequest.builder()
                .numberOfRecords(numberOfRecords)
                .year(year)
                .build();

        ResponseEntity<PriceReportResponse> response = restTemplate.postForEntity(url, request, PriceReportResponse.class);

        PriceReportResponse responseBody = response.getBody();

        assertThat(responseBody).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(responseBody.getYear()).isEqualTo(year);
        assertThat(responseBody.getNumberOfRecords()).isEqualTo(numberOfRecords);
        assertThat(responseBody.getTopPriceIncreases()).isNotEmpty();
        assertThat(responseBody.getTopPriceDecreases()).isNotEmpty();

        List<PriceChangeEntry> topPriceIncreases = responseBody.getTopPriceIncreases();
        assertThat(topPriceIncreases).hasSize(numberOfRecords);

        double previousIncrease = Double.POSITIVE_INFINITY;
        for (PriceChangeEntry entry : topPriceIncreases) {
            double value = entry.getPriceChange();
            assertThat(value).isGreaterThan(0.0);
            assertThat(value).isLessThanOrEqualTo(previousIncrease);
            previousIncrease = value;
        }

        List<PriceChangeEntry> topPriceDecreases = responseBody.getTopPriceDecreases();
        assertThat(topPriceDecreases).hasSize(numberOfRecords);

        double previousDecrease = Double.NEGATIVE_INFINITY;
        for (PriceChangeEntry entry : topPriceDecreases) {
            double value = entry.getPriceChange();
            assertThat(value).isLessThan(0.0);
            assertThat(value).isGreaterThanOrEqualTo(previousDecrease);
            previousDecrease = value;
        }
    }
}
