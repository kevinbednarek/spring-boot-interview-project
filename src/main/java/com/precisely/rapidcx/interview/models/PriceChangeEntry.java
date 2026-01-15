package com.precisely.rapidcx.interview.models;

import java.io.Serializable;
import java.time.LocalDate;

public class PriceChangeEntry implements Serializable {
    private String name;
    private double priceChange;
    private LocalDate effectiveDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(double priceChange) {
        this.priceChange = priceChange;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Override
    public String toString() {
        return "PriceChangeEntry{" +
                "name='" + name + '\'' +
                ", priceChange=" + priceChange +
                ", effectiveDate=" + effectiveDate +
                '}';
    }
}
