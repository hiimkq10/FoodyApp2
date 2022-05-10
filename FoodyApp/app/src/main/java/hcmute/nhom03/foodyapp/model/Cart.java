package hcmute.nhom03.foodyapp.model;

public class Cart {
    private int id;
    private int UserID;
    private Food food;
    private int quantity;

    public Cart() {
    }

    public Cart(int userID, Food food, int quantity) {
        UserID = userID;
        this.food = food;
        this.quantity = quantity;
    }

    public Cart(int id, int userID, Food food, int quantity) {
        this.id = id;
        UserID = userID;
        this.food = food;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
