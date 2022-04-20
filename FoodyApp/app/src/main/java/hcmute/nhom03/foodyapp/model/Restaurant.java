package hcmute.nhom03.foodyapp.model;

public class Restaurant {
    private String name, description;
    private Boolean delivery;
    private int image;

    public Restaurant() {
    }

    public Restaurant(String name, String description, Boolean delivery, int image) {
        this.name = name;
        this.description = description;
        this.delivery = delivery;
        this.image = image;
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
}
