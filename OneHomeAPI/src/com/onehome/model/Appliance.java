package com.onehome.model;
public class Appliance {
    private int id;
    private String propertyId;
    private String vendorId;
    private String applianceDescription;
    private String manufacturer;
    private String model;
    private String serialNumber;
    private String purchaseDate;
    private String createdBy;
    private String createdOn;
    private String updatedBy;
    private String updatedOn;

    public int getId() {
        return id;
    }
    public void setId(final int id) {
        this.id = id;
    }
    public String getPropertyId() {
        return propertyId;
    }
    public void setPropertyId(final String propertyId) {
        this.propertyId = propertyId;
    }
    public String getVendorId() {
        return vendorId;
    }
    public void setVendorId(final String vendorId) {
        this.vendorId = vendorId;
    }
    public String getApplianceDescription() {
        return applianceDescription;
    }
    public void setApplianceDescription(final String applianceDescription) {
        this.applianceDescription = applianceDescription;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(final String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public String getModel() {
        return model;
    }
    public void setModel(final String model) {
        this.model = model;
    }
    public String getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(final String serialNumber) {
        this.serialNumber = serialNumber;
    }
    public String getPurchaseDate() {
        return purchaseDate;
    }
    public void setPurchaseDate(final String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }
    public String getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(final String createdOn) {
        this.createdOn = createdOn;
    }
    public String getUpdatedBy() {
        return updatedBy;
    }
    public void setUpdatedBy(final String updatedBy) {
        this.updatedBy = updatedBy;
    }
    public String getUpdatedOn() {
        return updatedOn;
    }
    public void setUpdatedOn(final String updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public String toString() {
        return "Appliance [applianceDescription=" + applianceDescription + ", createdBy=" + createdBy + ", createdOn="
                + createdOn + ", id=" + id + ", manufacturer=" + manufacturer + ", model=" + model + ", propertyId="
                + propertyId + ", purchaseDate=" + purchaseDate + ", serialNumber=" + serialNumber + ", updatedBy="
                + updatedBy + ", updatedOn=" + updatedOn + ", vendorId=" + vendorId + "]";
    }

    
}