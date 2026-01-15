package com.precisely.rapidcx.interview.models;

import com.opencsv.bean.CsvBindByName;

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

    public String getNdcDescription() {
        return ndcDescription;
    }

    public void setNdcDescription(String ndcDescription) {
        this.ndcDescription = ndcDescription;
    }

    public String getNdc() {
        return ndc;
    }

    public void setNdc(String ndc) {
        this.ndc = ndc;
    }

    public String getOldNadacPerUnit() {
        return oldNadacPerUnit;
    }

    public void setOldNadacPerUnit(String oldNadacPerUnit) {
        this.oldNadacPerUnit = oldNadacPerUnit;
    }

    public String getNewNadacPerUnit() {
        return newNadacPerUnit;
    }

    public void setNewNadacPerUnit(String newNadacPerUnit) {
        this.newNadacPerUnit = newNadacPerUnit;
    }

    public String getClassificationForRateSetting() {
        return classificationForRateSetting;
    }

    public void setClassificationForRateSetting(String classificationForRateSetting) {
        this.classificationForRateSetting = classificationForRateSetting;
    }

    public String getPercentChange() {
        return percentChange;
    }

    public void setPercentChange(String percentChange) {
        this.percentChange = percentChange;
    }

    public String getPrimaryReason() {
        return primaryReason;
    }

    public void setPrimaryReason(String primaryReason) {
        this.primaryReason = primaryReason;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Override
    public String toString() {
        return "PriceReportRow{" +
                "ndcDescription='" + ndcDescription + '\'' +
                ", ndc='" + ndc + '\'' +
                ", oldNadacPerUnit='" + oldNadacPerUnit + '\'' +
                ", newNadacPerUnit='" + newNadacPerUnit + '\'' +
                ", classificationForRateSetting='" + classificationForRateSetting + '\'' +
                ", percentChange='" + percentChange + '\'' +
                ", primaryReason='" + primaryReason + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", effectiveDate='" + effectiveDate + '\'' +
                '}';
    }
}
