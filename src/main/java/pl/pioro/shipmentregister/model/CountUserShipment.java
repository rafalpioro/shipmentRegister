package pl.pioro.shipmentregister.model;

public class CountUserShipment {

    private String email;
    private long count;

    public CountUserShipment(String email, long count) {
        this.email = email;
        this.count = count;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
