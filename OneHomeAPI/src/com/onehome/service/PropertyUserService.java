package com.onehome.service;
import com.onehome.model.PropertyUser;
import com.onehome.data.PropertyUserDAO;
import com.onehome.utilities.*;
import java.sql.*;
import java.util.*;
public class PropertyUserService 
{
    static PropertyUserDAO propertyuserdao = new PropertyUserDAO();

    //Default Constructor
    public PropertyUserService() {
        // Establish connection once 
        propertyuserdao.establishConnection();
    }

    public ArrayList<PropertyUser> getAllPropertyUsers()
    {
        try {
            return propertyuserdao.getAllPropertyUsers();
        } catch (SQLException e) {
            return null;
        }
    }

    public String addPropertyUser(PropertyUser pu) 
    {
        String message = validateUserInput(pu);
        if (message.isEmpty()) 
        {
            try {
                propertyuserdao.savePropertyUser(pu);
            } catch (SQLException e) {
                message = "Unable to add propertyuser";
            }
        }
        return message;
    }

    private String validateUserInput(PropertyUser pu) 
    {
        // Required: PropertyID, UserID
        // Validate: Nothing for right now
        String message = "";
        if (pu.getPropertyId() == null || pu.getPropertyId().isEmpty())
            message += ErrorMessages.PROPERTYID_NULL;
        if (pu.getUserId() == null || pu.getUserId().isEmpty())
            message += ErrorMessages.USERID_NULL;
        return message;
    }

    public String updatePropertyUser(PropertyUser newRow)
    {
        PropertyUser oldRow = searchPropertyUserByID(newRow.getPropertyId());
        mergePropertyUsers(oldRow, newRow);
        String message = validateUserInput(oldRow);

        if (message.isEmpty())
        {
            try {
                propertyuserdao.UpdatePropertyUser(oldRow);
            } catch (SQLException e) {
                message = "Unable to update propertyuser with ID " + oldRow.getPropertyId();
            } 
        }
        return message;
    }

    public void mergePropertyUsers(PropertyUser oldRow, PropertyUser newRow)
    {
        if ( (newRow.getPropertyId() != null) && (!newRow.getPropertyId().equals(oldRow.getPropertyId())) )
            oldRow.setPropertyId(newRow.getPropertyId());            
        if ( (newRow.getUserId() != null) && (!newRow.getUserId().equals(oldRow.getUserId())))
            oldRow.setUserId(newRow.getUserId());

        //Change all "" fields to nulls before validating
        if ( oldRow.getPropertyId() != null && (oldRow.getPropertyId().isEmpty()))
            oldRow.setPropertyId(null);            
        if ( oldRow.getUserId() != null && (oldRow.getUserId().isEmpty()))
            oldRow.setUserId(null);
    }

    public String deletePropertyUser(int id) 
    {
        String message = "";
        try {
            propertyuserdao.deletePropertyUser(id);
        } catch (SQLException e) {
            message = "Unable to delete propertyuser with ID " + id;
        }
        return message;
	}

    public PropertyUser searchPropertyUserByID(String id) 
    {
        try {
            return propertyuserdao.getPropertyUserById(id);
        } catch (SQLException e) {
            return null;
        }
	}

}