package com.onehome.service;

import com.onehome.model.Warranty;
import com.onehome.data.WarrantyDAO;
import com.onehome.utilities.*;

import java.sql.*;
import java.util.*;

public class WarrantyService {
    
    static WarrantyDAO warrantydao = new WarrantyDAO();

    //Default Constructor
    public WarrantyService() {
        warrantydao.establishConnection();
    }

    public Warranty searchWarrantyByID(int id) {
		try {
            return warrantydao.getWarrantyById(id);
        } catch (SQLException e) {
            return null;
        }
    }
    
    public ArrayList<Warranty> getAllWarranties() {
		try {
            return warrantydao.getAllWarranties();
        } catch (SQLException e) {
            return null;
        }
    }
    
    public String addWarranty(Warranty w)
    {
		String message = validateUserInput(w);
        if (message.isEmpty()) 
        {
            try {
                warrantydao.saveWarranty(w);
            } catch (SQLException e) {
                message = "Unable to add Warranty";
            }
        }
        return message;
    }
    
    private String validateUserInput(Warranty w) 
    {
        //Required: ApplianceID, WarrantyStart, WarrantyEnd
        String message = "";
        if (w.getApplianceid() == null || w.getApplianceid().isEmpty())
            message += ErrorMessages.APPLIANCEID_NULLERROR;
        if (w.getWarrantystart() == null || w.getWarrantystart().isEmpty())
            message += ErrorMessages.WARRANTYSTART_NULLERROR;
        if (w.getWarrantyend() == null || w.getWarrantyend().isEmpty())
            message += ErrorMessages.WARRANTYEND_NULLERROR;
        
        //Validate: Date Format 
        if ((w.getWarrantystart() != null && !w.getWarrantystart().isEmpty()))
            if (!DateUtil.isDateValid(w.getWarrantystart()))
                message += ErrorMessages.WARANTYSTART_DATEFORMATERROR;
            else
                w.setWarrantystart(DateUtil.reformat(w.getWarrantystart(), DateUtil.DATEFORMAT_MM_DD_YYYY, DateUtil.DATEFORMAT_YYYYMMDD));
        if ((w.getWarrantyend() != null && !w.getWarrantyend().isEmpty()))
            if (!DateUtil.isDateValid(w.getWarrantyend()))
                message += ErrorMessages.WARRANTYEND_DATEFORMATERROR;
            else
                w.setWarrantyend(DateUtil.reformat(w.getWarrantyend(), DateUtil.DATEFORMAT_MM_DD_YYYY, DateUtil.DATEFORMAT_YYYYMMDD));

        return message;
    }

    public String deleteWarranty(int id) 
    {
		String message = "";
        try {
            warrantydao.deleteWarranty(id);
        } catch (SQLException e) {
            message = "Unable to delete Warranty with ID " + id;
        }
        return message;
    }
    
    public String updateWarranty(Warranty newRow) 
    {
		Warranty oldRow = searchWarrantyByID(newRow.getId());
        mergeWarranties(oldRow, newRow);
        String message = validateUserInput(oldRow);

        if (message.isEmpty())
        {
            try {
                warrantydao.UpdateWarranty(oldRow);
            } catch (SQLException e) {
                message = "Unable to update Warranty with ID " + oldRow.getId();
            } 
        }
        return message;
    }
    
    private void mergeWarranties(Warranty oldRow, Warranty newRow) 
    {
        if ( (newRow.getApplianceid() != null) && (!newRow.getApplianceid().equals(oldRow.getApplianceid())) )
            oldRow.setApplianceid(newRow.getApplianceid());            
        if ( (newRow.getWarrantystart() != null) && (!newRow.getWarrantystart().equals(oldRow.getWarrantystart())))
            oldRow.setWarrantystart(newRow.getWarrantystart());
        else if ((newRow.getWarrantystart() != null) && newRow.getWarrantystart().isEmpty())
            oldRow.setWarrantystart(newRow.getWarrantystart());
        else
            oldRow.setWarrantystart(DateUtil.reformat(oldRow.getWarrantystart(), DateUtil.DATEFORMAT_YYYYMMDD, DateUtil.DATEFORMAT_MM_DD_YYYY));
        if ( (newRow.getWarrantyend() != null) && (!newRow.getWarrantyend().equals(oldRow.getWarrantyend())))
            oldRow.setWarrantyend(newRow.getWarrantyend());
        else if ((newRow.getWarrantyend() != null) && newRow.getWarrantyend().isEmpty())
            oldRow.setWarrantyend(newRow.getWarrantyend());
        else
            oldRow.setWarrantyend(DateUtil.reformat(oldRow.getWarrantyend(), DateUtil.DATEFORMAT_YYYYMMDD, DateUtil.DATEFORMAT_MM_DD_YYYY));
        if ( (newRow.getWarrantycontact() != null) && (!newRow.getWarrantycontact().equals(oldRow.getWarrantycontact())))
            oldRow.setWarrantycontact(newRow.getWarrantycontact());

        //Change all "" fields to nulls before validating
        if ( oldRow.getApplianceid() != null && (oldRow.getApplianceid().isEmpty()))
            oldRow.setApplianceid(null);            
        if ( oldRow.getWarrantystart() != null && (oldRow.getWarrantystart().isEmpty()))
            oldRow.setWarrantystart(null);
        if ( oldRow.getWarrantyend() != null && (oldRow.getWarrantyend().isEmpty()))
            oldRow.setWarrantyend(null);
        if ( oldRow.getWarrantycontact() != null && (oldRow.getWarrantycontact().isEmpty()))
            oldRow.setWarrantycontact(null);
    }

    public ArrayList<Warranty> getWarrantiesByProperty(int id) {
		return null;
	}
}