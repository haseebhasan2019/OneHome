package com.onehome.service;

import com.onehome.model.Appliance;
import com.onehome.model.Property;
import com.onehome.model.Service;
import com.onehome.model.Users;
import com.onehome.data.PropertyDAO;
import com.onehome.data.UserDAO;
import com.onehome.utilities.*;

import java.sql.*;
import java.util.*;

public class PropertyService 
{
    static PropertyDAO propertydao = new PropertyDAO();
    static UserDAO userdao = new UserDAO(); 

    //Default Constructor
    public PropertyService() {
        // Establish connection once 
        propertydao.establishConnection();
        userdao.establishConnection();
    }

    public ArrayList<Property> getAllProperties()
    {
        try {
            return propertydao.getAllProperties();
        } catch (SQLException e) {
            return null;
        }

    }
    
    public ArrayList<Users> getAllUsers(int id)
    {
        try {
            return propertydao.getUsers(id);
        } catch (SQLException e) {
            return null;
        }
    }
    
    public ArrayList<Appliance> getAllPropertyAppliances(int id)
    {
        try {
            return propertydao.getPropertyAppliances(id);
        } catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<Service> getAllPropertyServices(int id)
    {
        try {
            return propertydao.getPropertyServices(id);
        } catch (SQLException e) {
            return null;
        }
    }
    
    public String addProperty(Property p) 
    {
        String message = validateUserInput(p);
        if (message.isEmpty()) 
        {
            try {
                propertydao.saveProperty(p);
                propertydao.savePropertyUser(p.getUserId());
            } catch (SQLException e) {
                message = "Unable to add property";
            }
        }
        return message;
    }
	
    private String validateUserInput(Property p) 
    {
        // Required: title, address1, city, state, zip, country
        // Validate: all date fields (valid format), zip code (min 5, max 10), phone number, state has to be 2 characters, size should be a number, movedOutDate > movedInDate
        String message = "";
        if (p.getTitle() == null || p.getTitle().isEmpty())
            message += ErrorMessages.TITLE_NULL_ERROR;
        if (p.getAddress1() == null || p.getAddress1().isEmpty())
            message += ErrorMessages.ADDRESS1_NULL_ERROR;
        if (p.getCity() == null || p.getCity().isEmpty())
            message += ErrorMessages.CITY_NULL_ERROR;
        if (p.getState() == null || p.getState().isEmpty())
            message += ErrorMessages.STATE_NULL_ERROR;
        else if (p.getState().length() != 2)
            message += ErrorMessages.STATE_LENGTH_ERROR;
        if (p.getZip() == null || p.getZip().isEmpty())
            message += ErrorMessages.ZIP_NULL_ERROR;
        else if (p.getZip().length() != 5 && p.getZip().length() != 10)
            message += ErrorMessages.ZIP_LENGTH_ERROR;
        if (p.getCountry() == null || p.getCountry().isEmpty())
            message += ErrorMessages.COUNTRY_NULL_ERROR;
        if (p.getSize() != null && !p.getSize().isEmpty()){
            try {
                Integer.parseInt(p.getSize());
            } catch (NumberFormatException e) {
                message += ErrorMessages.SIZE_TYPE_ERROR;
            }
        }

        //All Date Checks
        Boolean moveInNotBlank = (p.getMovedInDate() != null && !p.getMovedInDate().isEmpty());
        Boolean moveOutNotBlank = (p.getMovedOutDate() != null && !p.getMovedOutDate().isEmpty());
        Boolean moveInValid = p.getMovedInDate() == null || DateUtil.isDateValid(p.getMovedInDate());
        Boolean moveOutValid = p.getMovedOutDate() == null || DateUtil.isDateValid(p.getMovedOutDate());

        if ( moveInNotBlank )
            if (!moveInValid)
                message += ErrorMessages.DATE_IN_FORMAT_ERROR;
            else
                p.setMovedInDate(DateUtil.reformat(p.getMovedInDate(), DateUtil.DATEFORMAT_MM_DD_YYYY, DateUtil.DATEFORMAT_YYYYMMDD));


        if ( moveOutNotBlank )
            if (!moveOutValid)
                message += ErrorMessages.DATE_OUT_FORMAT_ERROR;
            else
                p.setMovedOutDate(DateUtil.reformat(p.getMovedOutDate(), DateUtil.DATEFORMAT_MM_DD_YYYY, DateUtil.DATEFORMAT_YYYYMMDD));

        if ( moveInNotBlank && moveOutNotBlank && moveInValid && moveOutValid && (!DateUtil.areDatesInOrder(p.getMovedInDate(), p.getMovedOutDate())) ) 
            message += ErrorMessages.DATE_LOGIC_ERROR;

        return message;
    }

    public String updateProperty(Property newRow)
    {
        Property oldRow = searchPropertyByID(newRow.getId());
        mergeProperties(oldRow, newRow);
        String message = validateUserInput(oldRow);

        if (message.isEmpty())
        {
            try {
                propertydao.UpdateProperty(oldRow);
            } catch (SQLException e) {
                message = "Unable to update property with ID " + oldRow.getId();
            } 
        }
        return message;
    }

    public void mergeProperties(Property oldRow, Property newRow)
    {
        if ( (newRow.getTitle() != null) && (!newRow.getTitle().equals(oldRow.getTitle())) )
            oldRow.setTitle(newRow.getTitle());            
        if ( (newRow.getAddress1() != null) && (!newRow.getAddress1().equals(oldRow.getAddress1())))
            oldRow.setAddress1(newRow.getAddress1());
        if ( (newRow.getAddress2() != null) && (!newRow.getAddress2().equals(oldRow.getAddress2())))
            oldRow.setAddress2(newRow.getAddress2());
        if ( (newRow.getCity() != null) && (!newRow.getCity().equals(oldRow.getCity())))
            oldRow.setCity(newRow.getCity());
        if ( (newRow.getState() != null) && (!newRow.getState().equals(oldRow.getState())))
            oldRow.setState(newRow.getState());
        if ( (newRow.getZip() != null) && (!newRow.getZip().equals(oldRow.getZip())))
            oldRow.setZip(newRow.getZip());
        if ( (newRow.getCountry() != null) && (!newRow.getCountry().equals(oldRow.getCountry())))
            oldRow.setCountry(newRow.getCountry());
        if (newRow.isPrimaryResidence() != oldRow.isPrimaryResidence())
            oldRow.setPrimaryResidence(newRow.isPrimaryResidence());
        if ( (newRow.getSize() != null) && (!newRow.getSize().equals(oldRow.getSize())))
            oldRow.setSize(newRow.getSize());
        if ( (newRow.getCurrentResident() != null) && (!newRow.getCurrentResident().equals(oldRow.getCurrentResident())))
            oldRow.setCurrentResident(newRow.getCurrentResident());
        
        
        if ( (newRow.getMovedInDate() != null) && (!newRow.getMovedInDate().equals(oldRow.getMovedInDate())))
            oldRow.setMovedInDate(newRow.getMovedInDate());
        else if ((newRow.getMovedInDate() != null) && newRow.getMovedInDate().isEmpty())
            oldRow.setMovedInDate(newRow.getMovedInDate());
        else
            oldRow.setMovedInDate(DateUtil.reformat(oldRow.getMovedInDate(), DateUtil.DATEFORMAT_YYYYMMDD, DateUtil.DATEFORMAT_MM_DD_YYYY));
        
        
        if ( (newRow.getMovedOutDate() != null) && (!newRow.getMovedOutDate().equals(oldRow.getMovedOutDate())))
            oldRow.setMovedOutDate(newRow.getMovedOutDate());
        else if ((newRow.getMovedOutDate() != null) && newRow.getMovedOutDate().isEmpty())
            oldRow.setMovedOutDate(newRow.getMovedOutDate());
        else
            oldRow.setMovedOutDate(DateUtil.reformat(oldRow.getMovedOutDate(), DateUtil.DATEFORMAT_YYYYMMDD, DateUtil.DATEFORMAT_MM_DD_YYYY));
        
        
        if ( (newRow.getCreatedBy() != null) && (!newRow.getCreatedBy().equals(oldRow.getCreatedBy())))
            oldRow.setCreatedBy(newRow.getCreatedBy());
        if ( (newRow.getUpdatedBy() != null) && (!newRow.getUpdatedBy().equals(oldRow.getUpdatedBy())))
            oldRow.setUpdatedBy(newRow.getUpdatedOn());

        //Change all "" fields to nulls before validating
        if ( oldRow.getTitle() != null && (oldRow.getTitle().isEmpty()))
            oldRow.setTitle(null);            
        if ( oldRow.getAddress1() != null && (oldRow.getAddress1().isEmpty()))
            oldRow.setAddress1(null);
        if ( oldRow.getAddress2() != null && (oldRow.getAddress2().isEmpty()))
            oldRow.setAddress2(null);
        if ( oldRow.getCity() != null && (oldRow.getCity().isEmpty()))
            oldRow.setCity(null);
        if ( oldRow.getState() != null && (oldRow.getState().isEmpty()))
            oldRow.setState(null);
        if ( oldRow.getZip() != null && (oldRow.getZip().isEmpty()))
            oldRow.setZip(null);
        if ( oldRow.getCountry() != null && (oldRow.getCountry().isEmpty()))
            oldRow.setCountry(null);
        if ( oldRow.getSize() != null && (oldRow.getSize().isEmpty()))
            oldRow.setSize(null);
        if ( oldRow.getCurrentResident() != null && (oldRow.getCurrentResident().isEmpty()))
            oldRow.setCurrentResident(null);
        if ( oldRow.getMovedInDate() != null && (oldRow.getMovedInDate().isEmpty()))
            oldRow.setMovedInDate(null);
        if ( oldRow.getMovedOutDate() != null && (oldRow.getMovedOutDate().isEmpty()))
            oldRow.setMovedOutDate(null);
        if ( oldRow.getCreatedBy() != null && (oldRow.getCreatedBy().isEmpty()))
            oldRow.setCreatedBy(null);
        if ( oldRow.getUpdatedBy() != null && (newRow.getUpdatedBy().isEmpty()))
            oldRow.setUpdatedBy(null);
    }

    public String deleteProperty(int id) 
    {
        String message = "";
        try {
            propertydao.deleteProperty(id);
        } catch (SQLException e) {
            message = "Unable to delete property with ID " + id;
        }
        return message;
	}

    public Property searchPropertyByID(int id) 
    {
        try {
            return propertydao.getPropertyById(id);
        } catch (SQLException e) {
            return null;
        }
	}
}