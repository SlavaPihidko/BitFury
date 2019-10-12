package model;

public class AvPriceFromblockchain {

    private long x;
    private Double y;

    public long getX() {
        return x;
    }

    public AvPriceFromblockchain withX(long x) {
        this.x = x;
        return this;
    }

    public Double getY() {
        return y;
    }

    public AvPriceFromblockchain withY(Double y) {
        this.y = y;
        return this;
    }

    @Override
    public String toString() {
        return "AvPriceFromblockchain{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AvPriceFromblockchain)) return false;

        AvPriceFromblockchain that = (AvPriceFromblockchain) o;

        if (getX() != that.getX()) return false;
        return getY() != null ? getY().equals(that.getY()) : that.getY() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getX() ^ (getX() >>> 32));
        result = 31 * result + (getY() != null ? getY().hashCode() : 0);
        return result;
    }

}
