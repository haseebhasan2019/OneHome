package com.onehome.data;

public class DbConstants 
{
    //Property Constants
    public static final int PROPERTY_ID = 1; 
    public static final int PROPERTY_TITLE = 2; 
    public static final int PROPERTY_ADDRESS1 = 3;
    public static final int PROPERTY_ADDRESS2 = 4;
    public static final int PROPERTY_CITY = 5;
    public static final int PROPERTY_STATE = 6;
    public static final int PROPERTY_ZIP = 7;
    public static final int PROPERTY_COUNTRY = 8;
    public static final int PROPERTY_PRIMARYRESIDENCE = 9;
    public static final int PROPERTY_SIZE = 10;
    public static final int PROPERTY_CURRENTRESIDENT = 11;
    public static final int PROPERTY_MOVEINDATE = 12;
    public static final int PROPERTY_MOVEOUTDATE = 13;
    public static final int PROPERTY_CREATEDON = 14;
    public static final int PROPERTY_CREATEDBY = 15;
    public static final int PROPERTY_UPDATEDON = 16;
    public static final int PROPERTY_UPDATEDBY = 17;

    //Appliance Constants
    public static final int APPLIANCE_ID = 1;
    public static final int APPLIANCE_PROPERTYID = 2;
    public static final int APPLIANCE_VENDORID = 3;
    public static final int APPLIANCE_DESCRIPTION = 4;
    public static final int APPLIANCE_MANUFACTURER = 5;
    public static final int APPLIANCE_MODEL = 6;
    public static final int APPLIANCE_SERIALNUMBER = 7;
    public static final int APPLIANCE_PURCHASEDATE = 8;
    public static final int APPLIANCE_CREATEDBY = 9;
    public static final int APPLIANCE_CREATEDON = 10;
    public static final int APPLIANCE_UPDATEDBY = 11;
    public static final int APPLIANCE_UPDATEDON = 12;

    //Vendor Constants
    public static final int VENDOR_ID = 1;
    public static final int VENDOR_VENDORNAME= 2;
    public static final int VENDOR_CONTACTNAME = 3; 
    public static final int VENDOR_PHONE = 4;
    public static final int VENDOR_WEBURL = 5;
    public static final int VENDOR_NOTES = 6;

    //Service Constants
    public static final int SERVICE_ID = 1; 
    public static final int SERVICE_PROPERTYID = 2;
    public static final int SERVICE_APPLIANCEID = 3;
    public static final int SERVICE_SERVICEDESCRIPTION = 4;
    public static final int SERVICE_COST = 5;
    public static final int SERVICE_ISFINANCED = 6;
    public static final int SERVICE_CONTRACTORNAME = 7;
    public static final int SERVICE_CONTRACTORPHONE = 8; 
    public static final int SERVICE_DATET = 9;
    public static final int SERVICE_CREATEDON = 10;
    public static final int SERVICE_CREATEDBY = 11;

    //Users Constants
    public static final int USERS_ID = 1; 
    public static final int USERS_FIRSTNAME = 2;
    public static final int USERS_LASTNAME = 3;
    public static final int USERS_EMAIL = 4;
    public static final int USERS_USERPASSWORD = 5;
    public static final int USERS_CREATEDON = 6;
    public static final int USERS_CREATEDBY = 7;

    //Warranty Constants
    public static final int WARRANTY_ID = 1;
    public static final int WARRANTY_APPLIANCEID = 2;
    public static final int WARRANTY_WARRANTYSTART = 3;
    public static final int WARRANTY_WARRANTYEND = 4;
    public static final int WARRANTY_WARRANTYCONTACT = 5;
 
    //ExpenseCategory Constants
    public static final int EXPENSECATEGORY_ID = 1;
    public static final int EXPENSECATEGORY_CATEGORY = 2;

    //Expense Constants
    public static final int EXPENSE_ID = 1;
    public static final int EXPENSE_PROPERTYID = 2;
    public static final int EXPENSE_VENDORID = 3;
    public static final int EXPENSE_EXPENSECATEGORYID = 4;
    public static final int EXPENSE_AMOUNT = 5;
    public static final int EXPENSE_DATEOFEXPENSE = 6;
    public static final int EXPENSE_ISRECURING = 7;
    public static final int EXPENSE_NOTES = 8;
    public static final int EXPENSE_CREATEDON = 9;
    public static final int EXPENSE_CREATEDBY = 10;

    //PropertyUser Constants
    public static final int PROPERTY_PUID = 1;
    public static final int USER_PUID = 2;
 
}