package hcmute.nhom03.foodyapp.model;

public class Order {
    private String id;
    private Integer userID;
    private String address;
    private Double total;

    public Order() {
    }

    public Order(String id, Integer userID, String address, Double total) {
        this.id = id;
        this.userID = userID;
        this.address = address;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
