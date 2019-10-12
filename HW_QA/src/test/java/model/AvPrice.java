package model;

import java.sql.Timestamp;

public class AvPrice {

    private Double average;
    private Timestamp ts;
    private boolean check;

    public Double getAverage() {
        return average;
    }

    public AvPrice withAverage(Double average) {
        this.average = average;
        return this;
    }

    public Timestamp getTs() {
        return ts;
    }

    public AvPrice withTs(Timestamp ts) {
        this.ts = ts;
        return this;
    }

    public boolean isCheck() {
        return check;
    }

    public AvPrice withCheck(boolean check) {
        this.check = check;
        return this;
    }

    @Override
    public String toString() {
        return "AvPrcice{" +
                "average=" + average +
                ", ts=" + ts +
                ", check=" + check +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AvPrice)) return false;

        AvPrice avPrcice = (AvPrice) o;

        if (isCheck() != avPrcice.isCheck()) return false;
        return getTs() != null ? getTs().equals(avPrcice.getTs()) : avPrcice.getTs() == null;
    }

    @Override
    public int hashCode() {
        int result = getTs() != null ? getTs().hashCode() : 0;
        result = 31 * result + (isCheck() ? 1 : 0);
        return result;
    }
}
