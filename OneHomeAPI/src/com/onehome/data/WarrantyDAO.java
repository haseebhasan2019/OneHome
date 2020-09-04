package com.onehome.data;

import com.onehome.model.Warranty;
import java.sql.*;
import java.util.*;

public class WarrantyDAO {
    
    Connection connection;
    Statement statement;
    String url = "jdbc:sqlserver://T450;databaseName=OneHomeDB;user=sa;";

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
    
    public Warranty getWarrantyById(int id) throws SQLException
    {
		Warranty w = new Warranty();
        statement = connection.createStatement();
        String sql = "SELECT * FROM Warranty WHERE ID = " + id;
        System.out.println("Executing: " + sql);
        ResultSet result = statement.executeQuery(sql);
        if (result.next()) {
            w.setId(result.getInt(DbConstants.WARRANTY_ID));
            w.setApplianceid(result.getString(DbConstants.WARRANTY_APPLIANCEID));
            w.setWarrantystart(result.getString(DbConstants.WARRANTY_WARRANTYSTART));
            w.setWarrantyend(result.getString(DbConstants.WARRANTY_WARRANTYEND));
            w.setWarrantycontact(result.getString(DbConstants.WARRANTY_WARRANTYCONTACT));
        } 
        return w;
    }
    
    public ArrayList<Warranty> getAllWarranties() throws SQLException
    {
		ArrayList<Warranty> warranties = new ArrayList<>();
        statement = connection.createStatement();
        String sql = "SELECT * FROM Warranty";
        System.out.println("Executing: " + sql);
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            Warranty w = new Warranty();
            w.setId(result.getInt(DbConstants.WARRANTY_ID));
            w.setApplianceid(result.getString(DbConstants.WARRANTY_APPLIANCEID));
            w.setWarrantystart(result.getString(DbConstants.WARRANTY_WARRANTYSTART));
            w.setWarrantyend(result.getString(DbConstants.WARRANTY_WARRANTYEND));
            w.setWarrantycontact(result.getString(DbConstants.WARRANTY_WARRANTYCONTACT));
            warranties.add(w);
        }
        return warranties;
    }
    
    public void saveWarranty(Warranty w) throws SQLException
    {
        // SQL Query
        String sql = "INSERT INTO Warranty ([ApplianceID],[WarrantyStart],[WarrantyEnd],[WarrantyContact]) VALUES (";

        sql += "" + w.getApplianceid() + ", ";
        sql += "" + w.getWarrantystart() + ", ";
        sql += "'" + w.getWarrantyend() + "', ";
        
        if (w.getWarrantycontact() == null || w.getWarrantycontact().equals("null"))
            sql += "null) ";
        else
            sql += "'" + w.getWarrantycontact() + "') ";

        System.out.println("Executing: " + sql);
        statement = connection.createStatement();
        statement.executeUpdate(sql);
    }
    
    public void deleteWarranty(int id) throws SQLException
    {
        String sql = "DELETE FROM Warranty WHERE ID = " + id;
        System.out.println(sql);
        statement = connection.createStatement();
        statement.executeUpdate(sql);
    }
    
    public void UpdateWarranty(Warranty w) throws SQLException
    {
        statement = connection.createStatement();
        String sql = "Update Warranty SET ";
        sql += "ApplianceID = " + w.getApplianceid() + ", ";
        sql += "WarrantyStart = " + w.getWarrantystart() + ", ";
        sql += "WarrantyEnd = '" + w.getWarrantyend() + "', ";
        
        if (w.getWarrantycontact() == null || w.getWarrantycontact().equals("null"))
            sql += "WarrantyContact = null ";
        else
            sql += "WarrantyContact = '" + w.getWarrantycontact() + "'";

        System.out.println("Executing: " + sql);
        statement.executeUpdate(sql);
	}
}