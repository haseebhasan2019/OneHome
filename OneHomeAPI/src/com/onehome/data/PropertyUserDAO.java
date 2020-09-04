package com.onehome.data;
import com.onehome.model.PropertyUser;
import java.sql.*;
import java.util.*;

public class PropertyUserDAO 
{
    Connection connection;
    Statement statement;
    String url = "jdbc:sqlserver://T450;databaseName=OneHomeDB;user=sa;password=adpadp10";

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
    
    public void savePropertyUser(PropertyUser pu) throws SQLException
    {
        // SQL Query
        String sql = "INSERT INTO PropertyUser ([PropertyId],[UserId]) VALUES (";

        sql += "'" + pu.getPropertyId() + "', ";
        sql += "'" + pu.getUserId() + "') ";

        System.out.println("Executing: " + sql);
        statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    public ArrayList<PropertyUser> getAllPropertyUsers() throws SQLException
    {
        ArrayList<PropertyUser> propertyUsers = new ArrayList<>();
        statement = connection.createStatement();
        String sql = "SELECT * FROM PropertyUser";
        System.out.println("Executing: " + sql);
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            PropertyUser pu = new PropertyUser();
            pu.setPropertyId(result.getString(DbConstants.PROPERTY_PUID));
            pu.setUserId(result.getString(DbConstants.USER_PUID));
            propertyUsers.add(pu);
        }
        return propertyUsers;
    }

    public PropertyUser getPropertyUserById(String id) throws SQLException
    {
        PropertyUser pu = new PropertyUser();
        //Create the statement object to connect
        statement = connection.createStatement();
        //Execute the statement and store query data
        String sql = "SELECT * FROM PropertyUser WHERE PropertyID = " + id;
        System.out.println("Executing: " + sql);
        ResultSet result = statement.executeQuery(sql);
        if (result.next()) {
            pu.setPropertyId(result.getString(DbConstants.PROPERTY_PUID));
            pu.setUserId(result.getString(DbConstants.USER_PUID));
        } 
        return pu;
    }

    public void UpdatePropertyUser(PropertyUser oldRow) throws SQLException
    {
        statement = connection.createStatement();
        String sql = "Update PropertyUser SET ";
        sql += "PropertyID = '" + oldRow.getPropertyId() + "',";
        sql += "UserID = '" + oldRow.getUserId() + "'";
        
        System.out.println("Executing: " + sql);
        statement.executeUpdate(sql);
    }

    public void deletePropertyUser(int id) throws SQLException 
    {
        String sql = "DELETE FROM PropertyUser WHERE PropertyID = " + id;
        System.out.println(sql);
        statement = connection.createStatement();
        statement.executeUpdate(sql);
    }
}