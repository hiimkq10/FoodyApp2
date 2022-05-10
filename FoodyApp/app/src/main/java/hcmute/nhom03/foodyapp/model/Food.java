package hcmute.nhom03.foodyapp.model;

import java.io.Serializable;

public class Food implements Serializable {
    private int id, resID;
    private String name, description;
    private int price;
    private int image;
    private boolean delivery;

    public Food() {
    }

    public Food(int id, int resID, String name, int price, int image, String description, Boolean delivery) {
        this.id = id;
        this.resID = resID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.delivery = delivery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResID() {
        return resID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }
}
