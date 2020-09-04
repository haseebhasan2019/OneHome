package com.onehome.utilities;

public class ErrorMessages 
{
    //Property 
    public static final String TITLE_NULL_ERROR = "Title cannot be null. \n"; 
    public static final String ADDRESS1_NULL_ERROR = "Address1 cannot be null. \n";
    public static final String CITY_NULL_ERROR = "City cannot be null. \n";
    public static final String STATE_NULL_ERROR = "State cannot be null. \n";
    public static final String ZIP_NULL_ERROR = "Zip cannot be null. \n";
    public static final String COUNTRY_NULL_ERROR = "Country cannot be null. \n";
    public static final String SIZE_TYPE_ERROR = "Size must be a number. \n";
    public static final String DATE_IN_FORMAT_ERROR = "Invalid moved in date format, Must be: MM-dd-yyyy. \n";
    public static final String DATE_OUT_FORMAT_ERROR = "Invalid moved out date format, Must be: MM-dd-yyyy. \n";
    public static final String ZIP_LENGTH_ERROR = "Zip input must be either 5 or 10 characters. \n";
    public static final String STATE_LENGTH_ERROR = "State input must be 2 character abbreviation for the state. \n";
    public static final String DATE_LOGIC_ERROR = "Moved In Date must be before Moved Out Date. \n";

    //Appliance Errors
    public static final String PROPERTYID_NULL_ERROR = "PropertyID cannot be null. \n";
    public static final String VENDORID_NULL_ERROR = "VendorID cannot be null. \n";
    public static final String DESCRIPTION_NULL_ERROR = "Appliance Description cannot be null. \n";
    public static final String CREATEDBY_NULL_ERROR = "Created By cannot be null. \n";
    public static final String PURCHASE_DATE_FORMAT_ERROR = "Invalid Purchase Date format, Must be: MM-dd-yyyy. \n";

    //Vendor Errors
    public static final String NULL_VENDORNAME = "VendorName is a required field \n"; 
    public static final String NULL_NOTES = "Notes is a required field \n";

    //Service Errors
    public static final String NULL_PROPERTYID = "PropertyID is a required field\n";
    public static final String NULL_SERVICEDESCRIPTION = "ServiceDescription is a required field\n";
    public static final String NULL_COST = "Cost is a required field\n";
    public static final String NULL_CONTRACTORNAME = "Contractor name is a required field\n";
    public static final String NULL_CONTRACTORPHONE = "Contractor phone is a required field\n";
    public static final String NULL_DATET = "DateT is a required field\n";
    public static final String INVALID_DATET = "DateT is invalid\n";
    public static final String INVALID_CONTRACTORPHONE = "Contractor phone number can not exceed 15 characters\n";
    
    //Users Error
    public static final String NULL_FIRSTNAME = "FirstName is a required field";
	public static final String NULL_LASTNAME = "Last Name is a required field";
	public static final String NULL_EMAIL = "Email is a required field";
    public static final String NULL_USERPASSWORD = "User password is a required field";
    
    //Category Errors
    public static final String NULL_CATEGORY = "Category cannot be a field field. \n";

    //Warranty Errors
    public static final String APPLIANCEID_NULLERROR = "PropertyID cannot be null. \n";
    public static final String WARRANTYSTART_NULLERROR = "WarrantyStart cannot be null. \n";
    public static final String WARRANTYEND_NULLERROR = "WarrantyEnd cannot be null. \n";
    public static final String WARANTYSTART_DATEFORMATERROR = "Invalid WarrantyStart Date Format, Enter in: MM-dd-yyyy. \n";
    public static final String WARRANTYEND_DATEFORMATERROR = "Invalid WarrantyEnd Date format, Must be: MM-dd-yyyy. \n";

    //Expense Errors , Amount, DateofExpense
    public static final String EXPENSECATEGORY_NULL_ERROR = "Expense Category cannot be null. \n";
    public static final String AMOUNT_NULL_ERROR = "Amount cannot be null. \n";
    public static final String DATEOFEXPENSE_NULL_ERROR = "Date of Expense cannot be null. \n";
    public static final String PROPERTYID_TYPE_ERROR = "Property ID must be an integer. \n";
    public static final String VENDORID_TYPE_ERROR = "Vendor ID must be an integer. \n";
    public static final String EXPENSECATEGORY_TYPE_ERROR = "Expense Category must be an integer. \n";
    public static final String AMOUNT_TYPE_ERROR = "Amount must be an integer. \n";
    public static final String DATEOFEXPENSE_FORMAT_ERROR = "Invalid Date of Expense format, Must be: MM-dd-yyyy. \n";

    //PropertyUser Errors
    public static final String PROPERTYID_NULL = "PropertyID is a required field!!! \n";
    public static final String USERID_NULL = "UserID is a required field!!! \n";

}