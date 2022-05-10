package hcmute.nhom03.foodyapp.model;

public class User {
    private  Integer id;
    private String name, phone, pass, address;

    public User() {
    }

    public User(Integer id, String name, String phone, String pass, String address) {

        this.id = id;
        this.name = name;
        this.phone = phone;
        this.pass = pass;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
