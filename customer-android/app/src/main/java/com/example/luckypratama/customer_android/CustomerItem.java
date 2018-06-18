package com.example.luckypratama.customer_android;

public class CustomerItem {
    private Long id;
    private String name;
    private String address;
    private String phone_number;
    private String email;

    public CustomerItem(Long id, String name, String address, String phone_number, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }
}
