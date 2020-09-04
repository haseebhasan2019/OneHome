package com.onehome.data;
import java.sql.*;
import java.util.ArrayList;
import com.onehome.model.*;

public class ServiceDAO {
    Connection connection;
    Statement statement;
    String url = "jdbc:sqlserver://localhost\\sqlexpress;databasename=OneHomeDB;user=sa;";

    public void establishConnection(){
        try{
            connection = DriverManager.getConnection(url);
            System.out.println("Connected to MS SQL Server");
        }
        catch(SQLException ex){
            System.out.println("Oops an exception was found");
            ex.printStackTrace();
        }
    } 

    public void saveService(Service s) throws SQLException {
        //initialize sql query
        String sql = "Insert into Service " + 
        "([PropertyID], [ApplianceID], [ServiceDescription], [Cost], [isFinanced], [ContractorName], [ContractorPhone], [DateT], [CreatedOn], [CreatedBy]) VALUES ("; 
        sql += "" + s.getPropertyId() + ", "; 
        sql += "" + s.getApplianceId() + ", "; 
        sql += "'" + s.getServiceDescription() + "', "; 
        sql += "" + s.getCost() + ", "; 
        if(s.isFinanced() == true){ 
            sql += "" + 1 + ", ";
        }
        else
            sql += "" + 0 + ", ";
        sql += "'" + s.getContractorName() + "', ";
        sql += "'" + s.getContractorPhone() + "', ";
        if (s.getDateT() == null || s.getDateT().equals("null"))
            sql += "null, ";
        else
            sql += "'" + s.getDateT() + "', ";
        
        sql += "getDate(), "; //CreatedOn should take current date: no need for input
        sql += "'" + s.getCreatedBy() + "')"; //Should link with user and not require assignment
        
        //Print and Execute sql statement
        System.out.println("executing: " + sql);
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }
    
    public ArrayList<Service> getAllServices() throws SQLException {
        ArrayList<Service> allServices = new ArrayList<Service>(); 
        statement = connection.createStatement();

        // Execute the statement and store query data
        ResultSet result = statement.executeQuery("SELECT * FROM Service");
        while(result.next()){
         Service s = new Service();
         s.setId(result.getInt(DbConstants.SERVICE_ID));
         s.setPropertyId(result.getString(DbConstants.SERVICE_PROPERTYID));
         s.setApplianceId(result.getString(DbConstants.SERVICE_APPLIANCEID));
         s.setServiceDescription(result.getString(DbConstants.SERVICE_SERVICEDESCRIPTION));
         s.setCost(result.getString(DbConstants.SERVICE_COST));
         s.setFinanced(result.getBoolean(DbConstants.SERVICE_ISFINANCED));
         s.setContractorName(result.getString(DbConstants.SERVICE_CONTRACTORNAME));
         s.setContractorPhone(result.getString(DbConstants.SERVICE_CONTRACTORPHONE));
         s.setDateT(result.getString(DbConstants.SERVICE_DATET));
         s.setCreatedOn(result.getString(DbConstants.SERVICE_CREATEDON));
         s.setCreatedBy(result.getString(DbConstants.SERVICE_CREATEDBY));
         allServices.add(s);    
     }
     return allServices;
    }
    
    public Service getServiceById(int id) throws SQLException {
        Service s = new Service();
        //Create the statement object to connect
        statement = connection.createStatement();
        //Execute the statement and store query data
        String sql = "SELECT * FROM Service WHERE ID=" + id;
        System.out.println("Executing: " + sql);
        ResultSet result = statement.executeQuery(sql);
        //Alter this code, read the result set and put the data in to a new Property
        if (result.next()) {          
         s.setId(result.getInt(DbConstants.SERVICE_ID));
         s.setPropertyId(result.getString(DbConstants.SERVICE_PROPERTYID));
         s.setApplianceId(result.getString(DbConstants.SERVICE_APPLIANCEID));
         s.setServiceDescription(result.getString(DbConstants.SERVICE_SERVICEDESCRIPTION));
         s.setCost(result.getString(DbConstants.SERVICE_COST));
         s.setFinanced(result.getBoolean(DbConstants.SERVICE_ISFINANCED));
         s.setContractorName(result.getString(DbConstants.SERVICE_CONTRACTORNAME));
         s.setContractorPhone(result.getString(DbConstants.SERVICE_CONTRACTORPHONE));
         s.setDateT(result.getString(DbConstants.SERVICE_DATET));
         s.setCreatedOn(result.getString(DbConstants.SERVICE_CREATEDON));
         s.setCreatedBy(result.getString(DbConstants.SERVICE_CREATEDBY));
        } 
        return s;
    }

    public void UpdateService(Service oldRow) throws SQLException {
        statement = connection.createStatement();
        String sql = "Update Service SET ";
              
        sql += "PropertyId = " + oldRow.getPropertyId() + ",";
        sql += "ApplianceId = " + oldRow.getApplianceId() + ",";
        sql += "ServiceDescription = '" + oldRow.getServiceDescription() + "',";
        sql += "Cost = " + oldRow.getCost() + ",";
        if (oldRow.isFinanced())
        {
            sql += "isFinanced = " + 1 + ",";
        }
        else
        {
            sql += "isFinanced = " + 0 + ",";
        }

        if(oldRow.getContractorName() == null){
            sql += "ContractorName = " + oldRow.getContractorName() + ",";
        }
        else{
            sql += "ContractorName = '" + oldRow.getContractorName() + "',";
        }
        if(oldRow.getContractorPhone() == null){
            sql += "ContractorPhone = " + oldRow.getContractorPhone() + ",";
        }
        else{
            sql += "ContractorPhone = '" + oldRow.getContractorPhone() + "',";
        }
        if(oldRow.getDateT() == null){
            sql += "DateT = " + oldRow.getDateT() + ",";
        }
        else{
            sql += "DateT = '" + oldRow.getDateT() + "',";
        }
        if(oldRow.getCreatedBy()!= null){
            sql += "CreatedBy = '" + oldRow.getCreatedBy() + "' ";
        }    
        
        // if (updatedRow.getUpdatedBy() != null)
        //     sql += "UpdatedBy = '" + updatedRow.getUpdatedBy() + "',";
        
        // sql += " UpdatedOn = getDate() WHERE Id = " + oldRow.getId();
        
        sql += "WHERE ID = " + oldRow.getId(); 
        System.out.println("Executing: " + sql);
        statement.executeUpdate(sql);
    }

    public void deleteService(int id) throws SQLException {
        statement = connection.createStatement();
        // Execute the statement and update
        String sql = "DELETE FROM Service WHERE ID = " + id;
        statement.executeUpdate(sql);
    }

}