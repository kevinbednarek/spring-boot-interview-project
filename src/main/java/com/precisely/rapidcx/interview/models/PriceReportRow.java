package com.precisely.rapidcx.interview.models;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceReportRow {
    @CsvBindByName(column = "NDC Description")
    private String ndcDescription;

    @CsvBindByName(column = "NDC")
    private String ndc;

    @CsvBindByName(column = "Old NADAC Per Unit")
    private String oldNadacPerUnit;

    @CsvBindByName(column = "New NADAC Per Unit")
    private String newNadacPerUnit;

    @CsvBindByName(column = "Classification for Rate Setting")
    private String classificationForRateSetting;

    @CsvBindByName(column = "Percent Change")
    private String percentChange;

    @CsvBindByName(column = "Primary Reason")
    private String primaryReason;

    @CsvBindByName(column = "Start Date")
    private String startDate;

    @CsvBindByName(column = "End Date")
    private String endDate;

    @CsvBindByName(column = "Effective Date")
    private String effectiveDate;
}
