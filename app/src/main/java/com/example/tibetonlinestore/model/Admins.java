package com.example.tibetonlinestore.model;

public class Admins
{
    private String name,phone,password;
    public Admins()
    {

    }

    public Admins(String name, String phone,String password) {
        this.name = name;
        this.phone = phone;
        this.password=password;
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
    public String getPassword() {
        return password;
    }
// this is dummy commit
    public void setPassword(String password) {
        this.password = password;
    }
}
