package hcmute.nhom03.foodyapp.model;

public class OrderDetail {
    private String orderID;
    private Integer foodID, quantity;

    public OrderDetail() {
    }

    public OrderDetail(String orderID, Integer foodID, Integer quantity) {
        this.orderID = orderID;
        this.foodID = foodID;
        this.quantity = quantity;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Integer getFoodID() {
        return foodID;
    }

    public void setFoodID(Integer foodID) {
        this.foodID = foodID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
