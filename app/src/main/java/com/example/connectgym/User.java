package com.example.connectgym;

import java.util.HashMap;

public class User {

    private String username;
    private String email;
    private HashMap<String, Integer> classRatings; // Key: class ID, Value: rating (1-5)

    public User(String username, String email, HashMap<String, Integer> classRatings) {
        this.username = username;
        this.email = email;
        this.classRatings = classRatings;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public HashMap<String, Integer> getClassRatings() {
        return classRatings;
    }
}
