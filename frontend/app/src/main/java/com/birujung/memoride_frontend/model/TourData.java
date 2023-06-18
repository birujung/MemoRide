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

    public TourData(int id, String title, String city, String photo, String distance, String s, String description, double price, int maxGroupSize, boolean featured) {
        this.id = id;
        this.title = title;
        this.city = city;
        this.photo = photo;
        this.price = price;
        this.featured = featured;
    }

    public TourData(int id, String title, String location, double price, boolean isFeatured) {
        this.id = id;
        this.title = title;
        this.city = location;
        this.price = price;
        this.featured = isFeatured;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public double getDistance() {
        return distance;
    }

    public String getPhoto() {
        return photo;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getMaxGroupSize() {
        return maxGroupSize;
    }

    public boolean isFeatured() {
        return featured;
    }
}
