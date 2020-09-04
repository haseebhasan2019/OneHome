package com.onehome.service;

import com.onehome.data.*;
import com.onehome.model.Service;
import com.onehome.utilities.DateUtil;
import com.onehome.utilities.ErrorMessages;

import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceService {
    String message = "";
    static ServiceDAO servicedao = new ServiceDAO(); 
   
    public ServiceService() 
    {
        servicedao.establishConnection();
    }
    public ArrayList<Service> getAllServices() 
    {
        try 
        {
            return servicedao.getAllServices();  
        }
        catch (SQLException e) 
        {   
            return null;
        }    
    }

    public String addService(Service s) 
    {
        
        String errorMessage = "";
        // errorMessage = validateUserInput(s);
        if (errorMessage.isEmpty())
        {
            try
            {
                servicedao.saveService(s);
                errorMessage = "Service saved";
            }
            catch (SQLException e)
            {
                errorMessage = "Unable to add service";
            }
        }
        return errorMessage;
    }
    
    public String updateService(Service newRow){
        
        Service oldRow = searchServiceByID(newRow.getId());
        Service updatedRow = mergeServices(oldRow, newRow);
        message = validateUserInput(updatedRow);
        
        if(message.isEmpty()){
            try 
        {
            servicedao.UpdateService(oldRow);
        }
        catch (SQLException e) 
        {
            message = "Unable to edit service with ID " + oldRow.getId();
        }
    }
        return message;
    }
    private String validateUserInput(Service s){
        String message = "";
        if (s.getPropertyId() == null || s.getPropertyId().isEmpty() )
        {
            message += ErrorMessages.NULL_PROPERTYID;
        }
        if (s.getServiceDescription() == null || s.getServiceDescription().isEmpty() )
        {
            message += ErrorMessages.NULL_SERVICEDESCRIPTION;
        }
        if (s.getCost() == null || s.getCost().isEmpty() )
        {
            message += ErrorMessages.NULL_COST;
        }
        if (s.getContractorName() == null || s.getContractorName().isEmpty() )
        {
            message += ErrorMessages.NULL_CONTRACTORNAME;
        }
        if (s.getContractorPhone() == null || s.getContractorPhone().isEmpty() )
        {
            message += ErrorMessages.NULL_CONTRACTORNAME;
        }
        if (s.getDateT() == null || s.getDateT().isEmpty() )
        {
            message += ErrorMessages.NULL_CONTRACTORNAME;
        }
        if(s.getContractorPhone().length() > 15)
        {
            message += ""; 
        }
        if (s.getDateT() != null && !s.getDateT().isEmpty()) 
        {
            if(!DateUtil.isDateValid(s.getDateT()))
            {
                message += ErrorMessages.INVALID_DATET;
            }
            else
            {
                s.setDateT(DateUtil.reformat(s.getDateT(), DateUtil.DATEFORMAT_MM_DD_YYYY, DateUtil.DATEFORMAT_YYYYMMDD));
            }
        }
        return message; 
    }
    public Service mergeServices(Service oldRow, Service newRow){
        if( newRow.getPropertyId() != null && !newRow.getPropertyId().equals(oldRow.getPropertyId()))
        {
            oldRow.setPropertyId((newRow.getPropertyId()));
        }
        if( newRow.getApplianceId() != null && !newRow.getApplianceId().equals(oldRow.getApplianceId()))
        {
            oldRow.setApplianceId((newRow.getApplianceId()));
        }
        if(newRow.getServiceDescription() != null && !newRow.getServiceDescription().equals(oldRow.getServiceDescription()))
        {
            oldRow.setServiceDescription((newRow.getServiceDescription()));
        }
        if(newRow.getCost() != null && !newRow.getCost().equals(oldRow.getCost()))
        {
            oldRow.setCost((newRow.getCost()));
        }
        if(newRow.getServiceDescription() != null && !newRow.getServiceDescription().equals(oldRow.getServiceDescription()))
        {
            oldRow.setServiceDescription((newRow.getServiceDescription()));
        }
        if(newRow.isFinanced() != oldRow.isFinanced())
        {
            oldRow.setFinanced(newRow.isFinanced());
        }
        if(newRow.getContractorName() != null && !newRow.getContractorName().equals(oldRow.getContractorName()))
        {
            oldRow.setContractorName((newRow.getContractorName()));
        }
        if(newRow.getContractorPhone() != null && !newRow.getContractorPhone().equals(oldRow.getContractorPhone()))
        {
            oldRow.setContractorPhone((newRow.getContractorPhone()));
        }
        if(newRow.getDateT() != null && !newRow.getDateT().equals(oldRow.getDateT()))
        {
            if(newRow.getDateT().isEmpty()){
                oldRow.setDateT(null);
            } 
            else{
                oldRow.setDateT(newRow.getDateT());
            }
        }
        else
        {
            oldRow.setDateT(DateUtil.reformat(oldRow.getDateT(), DateUtil.DATEFORMAT_YYYYMMDD, DateUtil.DATEFORMAT_MM_DD_YYYY));
        }
        if(newRow.getCreatedBy() != null && !newRow.getCreatedBy().equals(oldRow.getCreatedBy()))
        {
            oldRow.setCreatedBy(newRow.getCreatedBy());
        }
        
        // if "" is entered change to null
        if (oldRow.getPropertyId() != null && (oldRow.getPropertyId().isEmpty()))
            oldRow.setPropertyId(null);
        if (oldRow.getApplianceId() != null && (oldRow.getApplianceId().isEmpty()))
            oldRow.setApplianceId(null);
        if (oldRow.getPropertyId() != null && (oldRow.getPropertyId().isEmpty()))
            oldRow.setPropertyId(null);
        if (oldRow.getServiceDescription() != null && (oldRow.getServiceDescription().isEmpty()))
            oldRow.setServiceDescription(null);
        if (oldRow.getCost() != null && (oldRow.getCost().isEmpty()))
            oldRow.setCost(null);
        if (oldRow.getContractorName() != null && (oldRow.getContractorName().isEmpty()))
            oldRow.setContractorName(null);
        if (oldRow.getContractorPhone() != null && (oldRow.getContractorPhone().isEmpty()))
            oldRow.setContractorPhone(null);
        if (oldRow.getDateT() != null && (oldRow.getDateT().isEmpty()))
            oldRow.setDateT(null);
        if (oldRow.getCreatedBy() != null && (oldRow.getCreatedBy().isEmpty()))
            oldRow.setCreatedBy(null);
        
        return oldRow;
    }
    public String deleteService(int id) 
    {
        try
        {
            servicedao.deleteService(id);
        }
        catch (SQLException e) 
        {
            message = "Unable to delete service with ID " + id;
        }
        return message;
    }
    public Service searchServiceByID(int id) 
    {
        try
        {
            return servicedao.getServiceById(id);
        }
        catch (SQLException e)
        {
            return null;
        }
	}

}