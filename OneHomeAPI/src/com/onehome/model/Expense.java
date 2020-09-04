package com.onehome.model;
public class Expense {
    private int id;
    private String propertyId;
    private String vendorId;
    private String expenseCategoryId;
    private String amount;
    private String dateOfExpense;
    private boolean isRecurring;
    private String notes;
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
    public String getVendorId() {
        return vendorId;
    }
    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }
    public String getExpenseCategoryId() {
        return expenseCategoryId;
    }
    public void setExpenseCategoryId(String expenseCategoryId) {
        this.expenseCategoryId = expenseCategoryId;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getDateOfExpense() {
        return dateOfExpense;
    }
    public void setDateOfExpense(String dateOfExpense) {
        this.dateOfExpense = dateOfExpense;
    }
    public boolean isRecurring() {
        return isRecurring;
    }
    public void setRecurring(boolean isRecurring) {
        this.isRecurring = isRecurring;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
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
        return "Expense [amount=" + amount + ", createdBy=" + createdBy + ", createdOn=" + createdOn
                + ", dateOfExpense=" + dateOfExpense + ", expenseCategoryId=" + expenseCategoryId + ", id=" + id
                + ", isRecurring=" + isRecurring + ", notes=" + notes + ", propertyId=" + propertyId + ", vendorId="
                + vendorId + "]";
    }
}