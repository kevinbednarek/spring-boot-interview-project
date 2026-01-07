package com.precisely.rapidcx.interview.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PriceReportRequest {
    private int year;
    private int numberOfRecords;
}
