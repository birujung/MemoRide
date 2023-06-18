package com.birujung.memoride_frontend.model;

import com.google.gson.annotations.SerializedName;

public class TourData {
    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("city")
    private String city;

    @SerializedName("address")
    private String address;

    @SerializedName("distance")
    private double distance;

    @SerializedName("photo")
    private String photo;

    @SerializedName("description")
    private String description;

    @SerializedName("price")
    private double price;

    @SerializedName("max_group_size")
    private int maxGroupSize;

    @SerializedName("featured")
    private boolean featured;

    // Add getters and setters for the fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMaxGroupSize() {
        return maxGroupSize;
    }

    public void setMaxGroupSize(int maxGroupSize) {
        this.maxGroupSize = maxGroupSize;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }
}
