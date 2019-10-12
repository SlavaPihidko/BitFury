package model;

public class Txrefs {

    private long value;
    String spent;

    public long getValue() {
        return value;
    }

    public Txrefs withValue(int value) {
        this.value = value;
        return this;
    }

    public String getSpent() {
        return spent;
    }

    public Txrefs withSpent(String spent) {
        this.spent = spent;
        return this;
    }

    @Override
    public String toString() {
        return "Txrefs{" +
                "value=" + value +
                ", spent=" + spent +
                '}';
    }
}
