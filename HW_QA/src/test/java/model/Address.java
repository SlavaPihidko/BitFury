package model;


import java.util.Arrays;

public class Address {
    private long total_received;
    private long total_sent;
    private long balance;
    private Txrefs [] txrefs;

    public long getTotal_received() {
        return total_received;
    }

    public Address withTotal_received(long total_received) {
        this.total_received = total_received;
        return this;
    }

    public long getTotal_sent() {
        return total_sent;
    }

    public Address withTotal_sent(long total_sent) {
        this.total_sent = total_sent;
        return this;
    }

    public long getBalance() {
        return balance;
    }

    public Address withBalance(long balance) {
        this.balance = balance;
        return this;
    }

    public Txrefs[] getTxrefs() {
        return txrefs;
    }

    public Address withTxrefs(Txrefs[] txrefs) {
        this.txrefs = txrefs;
        return this;
    }

    @Override
    public String toString() {
        return "Address{" +
                "total_received=" + total_received +
                ", total_sent=" + total_sent +
                ", balance=" + balance +
                ", txrefs=" + Arrays.toString(txrefs) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (getTotal_received() != address.getTotal_received()) return false;
        if (getTotal_sent() != address.getTotal_sent()) return false;
        return getBalance() == address.getBalance();
    }

    @Override
    public int hashCode() {
        int result = (int) (getTotal_received() ^ (getTotal_received() >>> 32));
        result = 31 * result + (int) (getTotal_sent() ^ (getTotal_sent() >>> 32));
        result = 31 * result + (int) (getBalance() ^ (getBalance() >>> 32));
        return result;
    }
}
