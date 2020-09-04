package com.onehome.model;
public class Users {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String userpassword;
    private String createdon;
    private String createdby;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPassword() {
        return userpassword;
    }

    public void setUserPassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getCreatedOn() {
        return createdon;
    }

    public void setCreatedOn(String createdon) {
        this.createdon = createdon;
    }

    public String getCreatedBy() {
        return createdby;
    }

    public void setCreatedBy(String createdby) {
        this.createdby = createdby;
    }

}