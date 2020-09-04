package com.onehome.data;

import java.sql.*;
import java.util.ArrayList;
import com.onehome.model.*;

public class UserDAO {
    Connection connection;
    Statement statement;
    String url = "jdbc:sqlserver://DESKTOP-BE7NA5K;databaseName=OneHomeDB;user=sa;password=";
    
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
    
    public ArrayList<Users> getAllUsers() throws SQLException {
        ArrayList<Users> allUsers = new ArrayList<Users>(); 
        statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM Users");
        while (result.next()) {      
            Users u = new Users();     
            u.setId(result.getInt(DbConstants.USERS_ID));
            u.setFirstName(result.getString(DbConstants.USERS_FIRSTNAME));
            u.setLastName(result.getString(DbConstants.USERS_LASTNAME));
            u.setEmail(result.getString(DbConstants.USERS_EMAIL));
            u.setUserPassword(result.getString(DbConstants.USERS_USERPASSWORD));
            u.setCreatedOn(result.getString(DbConstants.USERS_CREATEDON));
            u.setCreatedBy(result.getString(DbConstants.USERS_CREATEDBY));
            allUsers.add(u);
        } 
        return allUsers; 
    }
    
    public void saveUser(Users u) throws SQLException {
        String sql = "Insert into Users" + 
        "([ID], [FirstName], [LastName], [Email], [UserPassword], [CreatedOn], [CreatedBy]) VALUES ("; 
        sql += "" + u.getId() + ", "; 
        sql += "" + u.getFirstName() + ", "; 
        sql += "'" + u.getLastName()+ "', "; 
        sql += "" + u.getEmail() + ", "; 
        sql += "'" + u.getUserPassword() + "', ";
        sql += "getDate(), "; //CreatedOn should take current date: no need for input
        sql += "'" + u.getCreatedBy() + "')"; //Should link with user and not require assignment
        //Print and Execute sql statement
        System.out.println("executing: " + sql);
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

	public Users getUserById(int id) throws SQLException {
        Users u = new Users();
        //Create the statement object to connect
        statement = connection.createStatement();
        //Execute the statement and store query data
        String sql = "SELECT * FROM Users WHERE ID=" + id;
        System.out.println("Executing: " + sql);
        ResultSet result = statement.executeQuery(sql);
        //Alter this code, read the result set and put the data in to a new Property
        if (result.next()) {          
         u.setId(result.getInt(DbConstants.USERS_ID));
         u.setFirstName(result.getString(DbConstants.USERS_FIRSTNAME));
         u.setLastName(result.getString(DbConstants.USERS_LASTNAME));
         u.setEmail(result.getString(DbConstants.USERS_EMAIL));
         u.setUserPassword(result.getString(DbConstants.USERS_USERPASSWORD));
         u.setCreatedOn(result.getString(DbConstants.USERS_CREATEDON));
         u.setCreatedBy(result.getString(DbConstants.USERS_CREATEDBY));
        } 
        return u;
	}

	public void deleteUser(int id) throws SQLException {
        statement = connection.createStatement();
        String sql = "DELETE FROM User WHERE ID = " + id;
        statement.executeUpdate(sql);
	}

	public void UpdateUser(Users oldRow) throws SQLException {
        statement = connection.createStatement();
        String sql = "Update Service SET ";
        sql += "FirstName = " + oldRow.getFirstName() + ", ";
        sql += "LastName = " + oldRow.getLastName() + ", ";
        sql += "Email = " + oldRow.getEmail() + ", ";
        sql += "UserPassword = " + oldRow.getUserPassword() + ", ";
        sql += "CreatedBy = '" + oldRow.getCreatedBy() + "' ";
        sql += "WHERE ID = " + oldRow.getId(); 
        System.out.println("Executing: " + sql);
        statement.executeUpdate(sql);
    }

    
}