package com.example.luckypratama.customer_android.model;

public class Customer {

    private Long id;
    private String name;
    private String address;
    private String phone_number;
    private String email;

    public Customer(Long id, String name, String address, String phone_number, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
