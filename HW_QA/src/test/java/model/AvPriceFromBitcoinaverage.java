package model;

import java.sql.Timestamp;

public class AvPriceFromBitcoinaverage {
    private String time;
    private Double average;

    public String getTime() {
        return time;
    }

    public AvPriceFromBitcoinaverage withTime(String time) {
        this.time = time;
        return this;
    }

    public Double getAverage() {
        return average;
    }

    public AvPriceFromBitcoinaverage withAverage(Double average) {
        this.average = average;
        return this;
    }

    @Override
    public String toString() {
        return "AvPriceFromBitcoinaverage{" +
                "time='" + time + '\'' +
                ", average=" + average +
                '}';
    }
}
