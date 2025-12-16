package HelperObjects;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PriceReportResponse {
    private int year;
    private int numberOfRecords;
    private List<PriceChangeEntry> topPriceIncreases;
    private List<PriceChangeEntry> topPriceDecreases;
}
