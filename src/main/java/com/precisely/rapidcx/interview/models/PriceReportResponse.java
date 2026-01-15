package com.precisely.rapidcx.interview.models;

import java.io.Serializable;
import java.util.List;

public class PriceReportResponse implements Serializable {
    private int year;
    private int numberOfRecords;
    private List<PriceChangeEntry> topPriceIncreases;
    private List<PriceChangeEntry> topPriceDecreases;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getNumberOfRecords() {
        return numberOfRecords;
    }

    public void setNumberOfRecords(int numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
    }

    public List<PriceChangeEntry> getTopPriceIncreases() {
        return topPriceIncreases;
    }

    public void setTopPriceIncreases(List<PriceChangeEntry> topPriceIncreases) {
        this.topPriceIncreases = topPriceIncreases;
    }

    public List<PriceChangeEntry> getTopPriceDecreases() {
        return topPriceDecreases;
    }

    public void setTopPriceDecreases(List<PriceChangeEntry> topPriceDecreases) {
        this.topPriceDecreases = topPriceDecreases;
    }

    @Override
    public String toString() {
        return "PriceReportResponse{" +
                "year=" + year +
                ", numberOfRecords=" + numberOfRecords +
                ", topPriceIncreases=" + topPriceIncreases +
                ", topPriceDecreases=" + topPriceDecreases +
                '}';
    }
}
