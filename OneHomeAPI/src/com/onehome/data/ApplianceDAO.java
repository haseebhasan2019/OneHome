package com.onehome.data;

import com.onehome.model.Appliance;
import java.sql.*;
import java.util.*;

public class ApplianceDAO {

    Connection connection;
    Statement statement;
    String url = "jdbc:sqlserver://FAMILYROOM-PC;databaseName=OneHomeDB;user=sa;";

    public void establishConnection()
    {
        try
        {
            //Establish Connection
            connection = DriverManager.getConnection(url);
            System.out.println("Connected to Microsoft SQL Server");
        }
        catch(SQLException e) 
        {
            System.out.println("There was an error connecting to Microsoft SQL Server");
            e.printStackTrace();
        }
	}

    public Appliance getApplianceById(int id) throws SQLException
    {
		Appliance a = new Appliance();
        statement = connection.createStatement();
        String sql = "SELECT * FROM Appliance WHERE ID = " + id;
        System.out.println("Executing: " + sql);
        ResultSet result = statement.executeQuery(sql);
        if (result.next()) {
            a.setId(result.getInt(DbConstants.APPLIANCE_ID));
            a.setPropertyId(result.getString(DbConstants.APPLIANCE_PROPERTYID));
            a.setVendorId(result.getString(DbConstants.APPLIANCE_VENDORID));
            a.setApplianceDescription(result.getString(DbConstants.APPLIANCE_DESCRIPTION));
            a.setManufacturer(result.getString(DbConstants.APPLIANCE_MANUFACTURER));
            a.setModel(result.getString(DbConstants.APPLIANCE_MODEL));
            a.setSerialNumber(result.getString(DbConstants.APPLIANCE_SERIALNUMBER));
            a.setPurchaseDate(result.getString(DbConstants.APPLIANCE_PURCHASEDATE));
            a.setCreatedOn(result.getString(DbConstants.APPLIANCE_CREATEDON));
            a.setCreatedBy(result.getString(DbConstants.APPLIANCE_CREATEDBY));
            a.setUpdatedOn(result.getString(DbConstants.APPLIANCE_UPDATEDON));
            a.setUpdatedBy(result.getString(DbConstants.APPLIANCE_UPDATEDBY));

        } 
        return a;
	}

    public ArrayList<Appliance> getAllAppliances() throws SQLException
    {
		ArrayList<Appliance> appliances = new ArrayList<>();
        statement = connection.createStatement();
        String sql = "SELECT * FROM Appliance";
        System.out.println("Executing: " + sql);
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            Appliance a = new Appliance();
            a.setId(result.getInt(DbConstants.APPLIANCE_ID));
            a.setPropertyId(result.getString(DbConstants.APPLIANCE_PROPERTYID));
            a.setVendorId(result.getString(DbConstants.APPLIANCE_VENDORID));
            a.setApplianceDescription(result.getString(DbConstants.APPLIANCE_DESCRIPTION));
            a.setManufacturer(result.getString(DbConstants.APPLIANCE_MANUFACTURER));
            a.setModel(result.getString(DbConstants.APPLIANCE_MODEL));
            a.setSerialNumber(result.getString(DbConstants.APPLIANCE_SERIALNUMBER));
            a.setPurchaseDate(result.getString(DbConstants.APPLIANCE_PURCHASEDATE));
            a.setCreatedOn(result.getString(DbConstants.APPLIANCE_CREATEDON));
            a.setCreatedBy(result.getString(DbConstants.APPLIANCE_CREATEDBY));
            a.setUpdatedOn(result.getString(DbConstants.APPLIANCE_UPDATEDON));
            a.setUpdatedBy(result.getString(DbConstants.APPLIANCE_UPDATEDBY));
            appliances.add(a);
        }
        return appliances;
	}

    public void saveAppliance(Appliance a) throws SQLException
    {
        // SQL Query
        String sql = "INSERT INTO Appliance ([PropertyID],[VendorID],[ApplianceDescription],[Manufacturer],[Model],[SerialNumber],[PurchaseDate],[CreatedBy],[CreatedOn]) VALUES (";

        sql += "" + a.getPropertyId() + ", ";
        sql += "" + a.getVendorId() + ", ";
        sql += "'" + a.getApplianceDescription() + "', ";
        
        if (a.getManufacturer() == null || a.getManufacturer().equals("null"))
            sql += "null, ";
        else
            sql += "'" + a.getManufacturer() + "', ";

        if (a.getModel() == null || a.getModel().equals("null"))
            sql += "null, ";
        else
            sql += "'" + a.getModel() + ", ";
        
        if (a.getSerialNumber() == null || a.getSerialNumber().equals("null"))
            sql += "null, ";
        else
            sql += "'" + a.getSerialNumber() + "', ";

        if (a.getPurchaseDate() == null || a.getPurchaseDate().equals("null"))
            sql += "null, ";
        else
            sql += "'" + a.getPurchaseDate() + "', ";

        // CreatedBy is a not null field - should it accept null?
        // if (a.getCreatedBy() == null || p.getCreatedBy().equals("null"))
        //     sql += "null, ";
        // else
            sql += "'" + a.getCreatedBy() + "', ";

        sql += "getDate())";

        System.out.println("Executing: " + sql);
        statement = connection.createStatement();
        statement.executeUpdate(sql);
	}

    public void deleteAppliance(int id) throws SQLException
    {
        String sql = "DELETE FROM Appliance WHERE ID = " + id;
        System.out.println(sql);
        statement = connection.createStatement();
        statement.executeUpdate(sql);
	}

    public void UpdateAppliance(Appliance a) throws SQLException
    {
        statement = connection.createStatement();
        String sql = "Update Appliance SET ";
        sql += "PropertyID = " + a.getPropertyId() + ", ";
        sql += "VendorID = " + a.getVendorId() + ", ";
        sql += "ApplianceDescription = '" + a.getApplianceDescription() + "', ";
        
        if (a.getManufacturer() == null || a.getManufacturer().equals("null"))
            sql += "Manufacturer = null, ";
        else
            sql += "Manufacturer = '" + a.getManufacturer() + "', ";

        if (a.getModel() == null || a.getModel().equals("null"))
            sql += "Model = null, ";
        else
            sql += "Model = '" + a.getModel() + ", ";
        
        if (a.getSerialNumber() == null || a.getSerialNumber().equals("null"))
            sql += "SerialNumber = null, ";
        else
            sql += "SerialNumber = '" + a.getSerialNumber() + "', ";

        if (a.getPurchaseDate() == null || a.getPurchaseDate().equals("null"))
            sql += "PurchaseDate = null, ";
        else
            sql += "PurchaseDate = '" + a.getPurchaseDate() + "', ";

        // CreatedBy is a not null field - should it accept null?
        // if (a.getCreatedBy() == null || p.getCreatedBy().equals("null"))
        //     sql += "null, ";
        // else
            sql += "CreatedBy = '" + a.getCreatedBy() + "', ";

        if (a.getUpdatedBy() == null || a.getUpdatedBy().equals("null"))
            sql += "UpdatedBy = null, ";
        else
            sql += "UpdatedBy = '" + a.getUpdatedBy() + "', ";

        sql += "UpdateOn = getDate() WHERE Id = " + a.getId();

        System.out.println("Executing: " + sql);
        statement.executeUpdate(sql);
	}

    public ArrayList<Appliance> getAppliancesByProperty() throws SQLException
    {
		return null;
	}
    
}