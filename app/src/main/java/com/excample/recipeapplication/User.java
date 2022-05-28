package com.excample.recipeapplication;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String name;
    private String Email;
    private String password;
    private List<String> menu =new ArrayList<>();

    public User() {
        super();
    }

    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        Email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getMenu() {
        return menu;
    }

    public void setMenu(List<String> menu) {
        this.menu = menu;
    }
}
