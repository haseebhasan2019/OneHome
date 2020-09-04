package com.onehome.model;
public class Property {
    private int id;
    private String title;
    private String address1;
    private String address2; 
    private String city;
    private String state;
    private String zip;
    private String country;
    private boolean primaryResidence;
    private String size;
    private String currentResident;
    private String movedInDate;
    private String movedOutDate;
    private String createdOn;
    private String createdBy;
    private String updatedOn;
    private String updatedBy;
    private int userId;

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

    public String getAddress1() {
        return address1;
    }
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isPrimaryResidence() {
        return this.primaryResidence;
    }
    public void setPrimaryResidence(boolean isPrimaryResidence) {
        this.primaryResidence = isPrimaryResidence;
    }

    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }

    public String getCurrentResident() {
        return currentResident;
    }
    public void setCurrentResident(String currentResident) {
        this.currentResident = currentResident;
    }

    public String getMovedInDate() {
        return movedInDate;
    }
    public void setMovedInDate(String movedInDate) {
        this.movedInDate = movedInDate;
    }

    public String getMovedOutDate() {
        return movedOutDate;
    }
    public void setMovedOutDate(String movedOutDate) {
        this.movedOutDate = movedOutDate;
    }

    public String getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }
    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }
 
    public String getUpdatedBy() {
        return updatedBy;
    }
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    @Override
    public String toString() {
        return "Property [address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", country=" + country
                + ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", currentResident=" + currentResident
                + ", id=" + id + ", movedInDate=" + movedInDate + ", movedOutDate=" + movedOutDate
                + ", primaryResidence=" + primaryResidence + ", size=" + size + ", state=" + state + ", title=" + title
                + ", updateBy=" + updatedBy + ", updatedOn=" + updatedOn + ", zip=" + zip + "]";
    }



}