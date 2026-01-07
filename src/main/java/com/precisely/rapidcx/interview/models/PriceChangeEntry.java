package com.precisely.rapidcx.interview.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PriceChangeEntry {
    private String name;
    private double priceChange;
    private LocalDate effectiveDate;
}
