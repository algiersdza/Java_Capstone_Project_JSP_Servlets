package com.group18.capstone.controller;

public class UserBuilder {
    private int userID;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String emailAddress;
    private String role;

    public UserBuilder() {
    }

    public UserBuilder setUserID(int userID) {
        this.userID = userID;
        return this;
    }

    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public UserBuilder setRole(String role) {
        this.role = role;
        return this;
    }

    public User createUser() {
        return new User(userID, firstName, lastName, userName, password, emailAddress, role);
    }
}