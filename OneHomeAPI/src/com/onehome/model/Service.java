package com.onehome.model;
public class Service {
    private int id;
    private String propertyId;
    private String ApplianceId;
    private String serviceDescription;
    private String cost;
    private boolean isFinanced;
    private String contractorName;
    private String contractorPhone;
    private String dateT;
    private String createdOn;
    private String createdBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getApplianceId() {
        return ApplianceId;
    }

    public void setApplianceId(String applianceId) {
        ApplianceId = applianceId;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public boolean isFinanced() {
        return isFinanced;
    }

    public void setFinanced(boolean isFinanced) {
        this.isFinanced = isFinanced;
    }

    public String getContractorName() {
        return contractorName;
    }

    public void setContractorName(String contractorName) {
        this.contractorName = contractorName;
    }

    public String getContractorPhone() {
        return contractorPhone;
    }

    public void setContractorPhone(String contractorPhone) {
        this.contractorPhone = contractorPhone;
    }

    public String getDateT() {
        return dateT;
    }

    public void setDateT(String dateT) {
        this.dateT = dateT;
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

    @Override
    public String toString() {
        return "Service [ApplianceId=" + ApplianceId + ", contactorPhone=" + contractorPhone + ", contractorName="
                + contractorName + ", cost=" + cost + ", createdBy=" + createdBy + ", createdOn=" + createdOn
                + ", dateT=" + dateT + ", id=" + id + ", isFinanced=" + isFinanced + ", propertyId=" + propertyId
                + ", serviceDescription=" + serviceDescription + "]";
    }
}