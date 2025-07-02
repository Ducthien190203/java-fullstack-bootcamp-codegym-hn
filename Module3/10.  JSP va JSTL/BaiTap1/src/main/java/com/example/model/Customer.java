package com.example.model;

public class Customer {
    private String name;
    private String birthday;
    private String address;
    private String image;

    public Customer(String name, String birthday, String address, String image) {
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }

    public String getImage() {
        return image;
    }
}
