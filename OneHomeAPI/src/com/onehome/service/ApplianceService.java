package com.onehome.service;

import com.onehome.model.Appliance;
import com.onehome.data.ApplianceDAO;
import com.onehome.utilities.*;

import java.sql.*;
import java.util.*;

public class ApplianceService {

    static ApplianceDAO appliancedao = new ApplianceDAO();

    //Default Constructor
    public ApplianceService() {
        appliancedao.establishConnection();
    }

	public Appliance searchApplianceByID(int id) {
		try {
            return appliancedao.getApplianceById(id);
        } catch (SQLException e) {
            return null;
        }
	}

	public ArrayList<Appliance> getAllAppliances() {
		try {
            return appliancedao.getAllAppliances();
        } catch (SQLException e) {
            return null;
        }
	}

    public String addAppliance(Appliance a) 
    {
		String message = validateUserInput(a);
        if (message.isEmpty()) 
        {
            try {
                appliancedao.saveAppliance(a);
            } catch (SQLException e) {
                message = "Unable to add Appliance";
            }
        }
        return message;
	}

    private String validateUserInput(Appliance a) 
    {
        //Required: PropertyID, VendorID, ApplianceDescription, CreatedBy
        String message = "";
        if (a.getPropertyId() == null || a.getPropertyId().isEmpty())
            message += ErrorMessages.PROPERTYID_NULL_ERROR;
        if (a.getVendorId() == null || a.getVendorId().isEmpty())
            message += ErrorMessages.VENDORID_NULL_ERROR;
        if (a.getApplianceDescription() == null || a.getApplianceDescription().isEmpty())
            message += ErrorMessages.DESCRIPTION_NULL_ERROR;
        if (a.getCreatedBy() == null || a.getCreatedBy().isEmpty())
            message += ErrorMessages.CREATEDBY_NULL_ERROR;
        
        //Validate: PurchaseDate 
        if ((a.getPurchaseDate() != null && !a.getPurchaseDate().isEmpty()))
            if (!DateUtil.isDateValid(a.getPurchaseDate()))
                message += ErrorMessages.PURCHASE_DATE_FORMAT_ERROR;
            else
                a.setPurchaseDate(DateUtil.reformat(a.getPurchaseDate(), DateUtil.DATEFORMAT_MM_DD_YYYY, DateUtil.DATEFORMAT_YYYYMMDD));

        return message;
    }

    public String deleteAppliance(int id) 
    {
		String message = "";
        try {
            appliancedao.deleteAppliance(id);
        } catch (SQLException e) {
            message = "Unable to delete Appliance with ID " + id;
        }
        return message;
	}

    public String updateAppliance(Appliance newRow) 
    {
		Appliance oldRow = searchApplianceByID(newRow.getId());
        mergeAppliances(oldRow, newRow);
        String message = validateUserInput(oldRow);

        if (message.isEmpty())
        {
            try {
                appliancedao.UpdateAppliance(oldRow);
            } catch (SQLException e) {
                message = "Unable to update Appliance with ID " + oldRow.getId();
            } 
        }
        return message;
	}

    private void mergeAppliances(Appliance oldRow, Appliance newRow) 
    {
        if ( (newRow.getPropertyId() != null) && (!newRow.getPropertyId().equals(oldRow.getPropertyId())) )
            oldRow.setPropertyId(newRow.getPropertyId());            
        if ( (newRow.getVendorId() != null) && (!newRow.getVendorId().equals(oldRow.getVendorId())))
            oldRow.setVendorId(newRow.getVendorId());
        if ( (newRow.getApplianceDescription() != null) && (!newRow.getApplianceDescription().equals(oldRow.getApplianceDescription())))
            oldRow.setApplianceDescription(newRow.getApplianceDescription());
        if ( (newRow.getManufacturer() != null) && (!newRow.getManufacturer().equals(oldRow.getManufacturer())))
            oldRow.setManufacturer(newRow.getManufacturer());
        if ( (newRow.getModel() != null) && (!newRow.getModel().equals(oldRow.getModel())))
            oldRow.setModel(newRow.getModel());
        if ( (newRow.getSerialNumber() != null) && (!newRow.getSerialNumber().equals(oldRow.getSerialNumber())))
            oldRow.setSerialNumber(newRow.getSerialNumber());
        if ( (newRow.getPurchaseDate() != null) && (!newRow.getPurchaseDate().equals(oldRow.getPurchaseDate())))
            oldRow.setPurchaseDate(newRow.getPurchaseDate());
        else if ((newRow.getPurchaseDate() != null) && newRow.getPurchaseDate().isEmpty())
            oldRow.setPurchaseDate(newRow.getPurchaseDate());
        else
            oldRow.setPurchaseDate(DateUtil.reformat(oldRow.getPurchaseDate(), DateUtil.DATEFORMAT_YYYYMMDD, DateUtil.DATEFORMAT_MM_DD_YYYY));
        if ( (newRow.getCreatedBy() != null) && (!newRow.getCreatedBy().equals(oldRow.getCreatedBy())))
            oldRow.setCreatedBy(newRow.getCreatedBy());
        if ( (newRow.getUpdatedBy() != null) && (!newRow.getUpdatedBy().equals(oldRow.getUpdatedBy())))
            oldRow.setUpdatedBy(newRow.getUpdatedOn());

        //Change all "" fields to nulls before validating
        if ( oldRow.getPropertyId() != null && (oldRow.getPropertyId().isEmpty()))
            oldRow.setPropertyId(null);            
        if ( oldRow.getVendorId() != null && (oldRow.getVendorId().isEmpty()))
            oldRow.setVendorId(null);
        if ( oldRow.getApplianceDescription() != null && (oldRow.getApplianceDescription().isEmpty()))
            oldRow.setApplianceDescription(null);
        if ( oldRow.getManufacturer() != null && (oldRow.getManufacturer().isEmpty()))
            oldRow.setManufacturer(null);
        if ( oldRow.getModel() != null && (oldRow.getModel().isEmpty()))
            oldRow.setModel(null);
        if ( oldRow.getSerialNumber() != null && (oldRow.getSerialNumber().isEmpty()))
            oldRow.setSerialNumber(null);
        if ( oldRow.getPurchaseDate() != null && (oldRow.getPurchaseDate().isEmpty()))
            oldRow.setPurchaseDate(null);
        if ( oldRow.getCreatedBy() != null && (oldRow.getCreatedBy().isEmpty()))
            oldRow.setCreatedBy(null);
        if ( oldRow.getUpdatedBy() != null && (newRow.getUpdatedBy().isEmpty()))
            oldRow.setUpdatedBy(null);
    }

    public ArrayList<Appliance> getAppliancesByProperty(int id) 
    {
		try {
            return appliancedao.getAppliancesByProperty();
        } catch (SQLException e) {
            return null;
        }
	}
    
}