package com.onehome.data;
import java.sql.*;
import java.util.ArrayList;
import com.onehome.model.*;

public class VendorDAO {
    Connection connection;
    Statement statement;
    String url = "jdbc:sqlserver://FAMILYROOM-PC;databaseName=OneHomeDB;user=sa;";
    
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

    public void saveVendor(Vendor v) throws SQLException
    {
        // SQL Query sending it into Vendor
        String sql = "INSERT INTO Vendor ([VendorName],[ContactName],[Phone],[WebURL],[Notes]) VALUES (";

        sql += "'" + v.getVendorName() + "', ";

        if (v.getContactName() == null || v.getContactName().equals("null"))
            sql += "null, ";
        else
            sql += "'" + v.getContactName() + "', ";

        if (v.getPhone() == null || v.getPhone().equals("null"))
            sql += "null, ";
        else
            sql += "'" + v.getPhone() + "', ";

        if (v.getWebURL() == null || v.getWebURL().equals("null"))
            sql += "null, ";
        else
            sql += "'" + v.getWebURL() + "', ";

        sql += "'" + v.getNotes() + "')";     

        System.out.println("executing: " + sql);
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    public ResultSet getAllVendor() throws SQLException {
        // Create the statement object to connect
        statement = connection.createStatement();
        // Execute the statement and store query data
        ResultSet result = statement.executeQuery("SELECT * FROM Vendor");
        return result;
     }

     public Vendor getVendorById(int id) throws SQLException {
        Vendor v = new Vendor();
        //Create the statement object to connect
        statement = connection.createStatement();
        //Execute the statement and store query data
        String sql = "SELECT * FROM Vendor WHERE ID=" + id;
        System.out.println("Executing: " + sql);
        ResultSet result = statement.executeQuery(sql);
        //Alter this code, read the result set and put the data in to a new Vendor
        if (result.next()) {
            System.out.println("Result Found: ");
            v.setId(result.getInt(DbConstants.VENDOR_ID));
            v.setVendorName(result.getString(DbConstants.VENDOR_VENDORNAME));
            v.setContactName(result.getString(DbConstants.VENDOR_CONTACTNAME));
            v.setPhone(result.getString(DbConstants.VENDOR_PHONE));
            v.setWebURL(result.getString(DbConstants.VENDOR_WEBURL));
            v.setNotes(result.getString(DbConstants.VENDOR_NOTES));
        } 
        return v;
    }

    public void UpdateVendor(Vendor updatedRow) throws SQLException {
        statement = connection.createStatement();
        String sql = "Update Vendor SET ";

        // if newRow does not equal oldRow and newRow is not an enter then update
            
        sql += "VendorName = '" + updatedRow.getVendorName() + "',";
        
        if (updatedRow.getContactName() != null)
            sql += "ContactName = '" + updatedRow.getContactName() + "',";
        else
            sql += "ContactName = null, ";
      
        if (updatedRow.getPhone() != null)
            sql += "Phone = " + updatedRow.getPhone() + ",";
        else
            sql += "Phone = null, ";        

        if (updatedRow.getWebURL() != null)
            sql += "WebURL = '" + updatedRow.getWebURL() + "',";
        else
            sql += "WebURL = null, ";  
        
        sql += "Notes = '" + updatedRow.getNotes() + "'"; 
            
        sql += "WHERE Id = " + updatedRow.getId();
        System.out.println("Executing: " + sql);
        statement.executeUpdate(sql);
    }

    public void deleteVendor(int id) throws SQLException {
        statement = connection.createStatement();
        // Execute the statement and update
        String sql = "DELETE FROM Vendor WHERE ID = " + id;
        statement.executeUpdate(sql);
    }

    public ArrayList<Vendor> getAllVendors() throws SQLException
    {
        ArrayList<Vendor> vendors = new ArrayList<>(); //Creates an Arraylist of Vendor Objects (vendors)
        statement = connection.createStatement();
        String sql = "SELECT * FROM Vendor";
        System.out.println("Executing: " + sql);
        ResultSet result = statement.executeQuery(sql); //ResultSet result contains the whole vendor table data from SQL server
        while (result.next()) { //Will go through each row of the vendor table
            Vendor v = new Vendor(); //Create a new, empty Vendor Object then start setting each field to the current row's field

            v.setId(result.getInt(DbConstants.VENDOR_ID));
            v.setVendorName(result.getString(DbConstants.VENDOR_VENDORNAME));
            v.setContactName(result.getString(DbConstants.VENDOR_CONTACTNAME));
            v.setPhone(result.getString(DbConstants.VENDOR_PHONE));
            v.setWebURL(result.getString(DbConstants.VENDOR_WEBURL));
            v.setNotes(result.getString(DbConstants.VENDOR_NOTES));
            vendors.add(v); // then add this full vendor object to the list of vendors (it adds it to the end)
        }
        return vendors; //Finally return the complete array list
    }
}