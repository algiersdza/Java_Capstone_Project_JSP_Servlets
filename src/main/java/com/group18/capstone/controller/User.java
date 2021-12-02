package com.group18.capstone.controller;

public class User {

    private int UserID;
    private String FirstName;
    private String LastName;
    private String UserName;
    private String Password;
    private String EmailAddress;
    private String Role;

    public User(){}

    public User(int userID, String firstName, String lastName, String userName, String password, String emailAddress, String role) {
        this.UserID = userID;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.UserName = userName;
        this.Password = password;
        this.EmailAddress = emailAddress;
        this.Role = role;
    }

    public int getUserID() {
        return UserID;
    }

    public String getRole() {
        return Role;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

}
