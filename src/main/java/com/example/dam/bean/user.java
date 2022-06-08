package com.example.dam.bean;

public class user {
    private String name,passworld,id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassworld() {
        return passworld;
    }

    public void setPassworld(String passworld) {
        this.passworld = passworld;
    }

    public user(String name, String passworld, String id) {
        this.name = name;
        this.passworld = passworld;
        this.id = id;
    }
    public user(){}
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
