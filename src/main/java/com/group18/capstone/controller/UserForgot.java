package com.group18.capstone.controller;
//class for password recovery
public class UserForgot {
    private String FirstName;
    private String LastName;
    private String EmailAddress;
    private String UserName;
    private String Password;

    public String getUserName(){return UserName;}
    public void setUserName(String UserName){this.UserName = UserName;}

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getPassword() {
        return Password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.EmailAddress = emailAddress;
    }




}
