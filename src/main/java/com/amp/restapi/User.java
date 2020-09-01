package com.amp.restapi;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
    @Id 
    private String id;
    
    @Indexed(unique=true)
    private String email;
    private String name;
    private String password;
    private Date lastLogin;

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getId() {
        return this.id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastLogin(Date date) {
        lastLogin = date;
    }

    public Date getLastLogin() {
        return lastLogin;
    }
}