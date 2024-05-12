package com.example.connectgym;

public class Gym {
    private String name;
    private String location;
    private String priceRange;
    private String classType;
    private int imageResourceId;

    public Gym(String name, String location, String priceRange, String classType, int imageResourceId) {
        this.name = name;
        this.location = location;
        this.priceRange = priceRange;
        this.classType = classType;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public String getClassType() {
        return classType;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
