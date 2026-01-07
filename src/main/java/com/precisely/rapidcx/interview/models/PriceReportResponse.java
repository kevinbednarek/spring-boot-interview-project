package com.precisely.rapidcx.interview.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PriceReportResponse implements Serializable {
    private int year;
    private int numberOfRecords;
    private List<PriceChangeEntry> topPriceIncreases;
    private List<PriceChangeEntry> topPriceDecreases;
}
