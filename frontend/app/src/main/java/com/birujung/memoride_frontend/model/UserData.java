package com.birujung.memoride_frontend.model;

import com.google.gson.annotations.SerializedName;

public class UserData {
    @SerializedName("id")
    private int id;

    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private String email;

    @SerializedName("photo")
    private String photo;

    @SerializedName("role")
    private String role;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("gender")
    private String gender;

    @SerializedName("phone_num")
    private String phoneNumber;

    @SerializedName("membership_level")
    private String membershipLevel;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoto() {
        return photo;
    }

    public String getRole() {
        return role;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}

