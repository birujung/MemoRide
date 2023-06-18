package com.birujung.memoride_frontend.model;

import com.google.gson.annotations.SerializedName;

public class UserData {
    @SerializedName("id")
    private int id;

    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private String email;

    private String password;

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

    private String token;

    public UserData() {
        this.username = username;
        this.email = email;
        this.role = role;
        this.fullName = fullName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.membershipLevel = membershipLevel;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() { return password; }

    public String getPhoto() {
        return photo;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }
    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}

