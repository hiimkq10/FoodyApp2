package hcmute.nhom03.foodyapp.model;

import java.io.Serializable;

public class Restaurant implements Serializable {
    private int id;
    private String name, description;
    private Boolean delivery;
    private int image;
    private String openHours;
    private String address;

    public Restaurant() {
    }

    public Restaurant(String name, String description, Boolean delivery, int image, String openHours, String address) {
        this.name = name;
        this.description = description;
        this.delivery = delivery;
        this.image = image;
        this.openHours = openHours;
        this.address = address;
    }

    public Restaurant(int id, String name, String description, Boolean delivery, int image, String openHours, String address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.delivery = delivery;
        this.image = image;
        this.openHours = openHours;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDelivery() {
        return delivery;
    }

    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getOpenHours() {
        return openHours;
    }

    public void setOpenHours(String openHours) {
        this.openHours = openHours;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
