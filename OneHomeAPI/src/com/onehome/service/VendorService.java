package com.onehome.service;
import com.onehome.model.*;
import com.onehome.data.*;
import java.sql.*;
import java.util.ArrayList;
import com.onehome.utilities.*;

public class VendorService {
    static VendorDAO vendordao = new VendorDAO();
    String message = "";
    
    public VendorService() {
        // Establish connection once 
        vendordao.establishConnection();
    }

    public ResultSet displayAllVendors() 
    {
        try 
        {
            return vendordao.getAllVendor();
        }
        catch (SQLException e) 
        {
            return null;
        }    
    }

    public String addVendor(Vendor v) 
    {
        // Step 1: Take user input for all the Vendor fields
        // Step 2: Create a vendor object with all the fields
        // Step 3: Validate User Input
        // Step 4: Call the DAO method saveVendor() with the vendor Object
        
        String errorMessage = validateUserInput(v);
        if (errorMessage.isEmpty())
        {
            try
            {
                vendordao.saveVendor(v);
                errorMessage = "Vendor saved";
            }
            catch (SQLException e)
            {
                errorMessage = "Unable to add vendor";
            }
        }
        return errorMessage;
    }

    private String validateUserInput(Vendor v) 
    {   String messages = "";

        if (v.getVendorName().isEmpty() || v.getVendorName().equals(null))
        {
            messages += ErrorMessages.NULL_VENDORNAME;
        }
        if (v.getNotes().isEmpty() || v.getNotes().equals(null))
        {
            messages += ErrorMessages.NULL_NOTES;
        }
    return messages; 
    }

    public String updateVendor(Vendor newRow)
    {
        Vendor oldRow = searchVendorByID(newRow.getId());
        mergeVendors(oldRow, newRow);
        String message = validateUserInput(oldRow);

        if (message.isEmpty())
        {
            try {
                vendordao.UpdateVendor(oldRow);
            } catch (SQLException e) {
                message = "Unable to update vendor with ID " + oldRow.getId();
            } 
        }
        return message;
    }

    public void mergeVendors(Vendor oldRow, Vendor newRow)
    {
        if ( (newRow.getVendorName() != null) && (!newRow.getVendorName().equals(oldRow.getVendorName())))
            oldRow.setVendorName(newRow.getVendorName());            
        if ( (newRow.getContactName() != null) && (!newRow.getContactName().equals(oldRow.getContactName())))
            oldRow.setContactName(newRow.getContactName());
        if ( (newRow.getPhone() != null) && (!newRow.getPhone().equals(oldRow.getPhone())))
            oldRow.setPhone(newRow.getPhone());
        if ( (newRow.getWebURL() != null) && (!newRow.getWebURL().equals(oldRow.getWebURL())))
            oldRow.setWebURL(newRow.getWebURL());
        if ( (newRow.getNotes() != null) && (!newRow.getNotes().equals(oldRow.getNotes())))
            oldRow.setNotes(newRow.getNotes());
    }

    public String deleteVendor(int id) 
    {
        try
        {
            vendordao.deleteVendor(id);
        }
        catch (SQLException e) 
        {
            message = "Unable to delete vendor with ID " + id;
        }
        return message;
    }
    
    public Vendor searchVendorByID(int id) 
    {
        try
        {
            return vendordao.getVendorById(id);
        }
        catch (SQLException e)
        {
            return null;
        }
    }

    public ArrayList<Vendor> getAllVendors()
    {
        try {
            return vendordao.getAllVendors();
        } catch (SQLException e) {
            return null;
        }
    }
}