package com.onehome.model;
public class Warranty {
    private int id;
    private String applianceid;
    private String warrantystart;
    private String warrantyend;
    private String warrantycontact;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApplianceid() {
        return applianceid;
    }

    public void setApplianceid(String applianceid) {
        this.applianceid = applianceid;
    }

    public String getWarrantystart() {
        return warrantystart;
    }

    public void setWarrantystart(String warrantystart) {
        this.warrantystart = warrantystart;
    }

    public String getWarrantyend() {
        return warrantyend;
    }

    public void setWarrantyend(String warrantyend) {
        this.warrantyend = warrantyend;
    }

    public String getWarrantycontact() {
        return warrantycontact;
    }

    public void setWarrantycontact(String warrantycontact) {
        this.warrantycontact = warrantycontact;
    }
}