package com.amp.restapi;

public class UserUpdate {
    public String name;
    public String password;

    public UserUpdate(String name, String password) {
        this.name = name;
        this.password = password;
    }
}