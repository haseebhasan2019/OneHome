package com.onehome.data;
import java.sql.*;
import java.util.ArrayList;
import com.onehome.model.*;

public class ExpenseCategoryDAO {
    Connection connection;
    Statement statement;
    String url = "jdbc:sqlserver://T450;databasename=OneHomeDB;user=sa;";
    
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

    public void saveExpenseCategory(ExpenseCategory e) throws SQLException {
        //initialize sql query
        String sql = "Insert into ExpenseCategory " +  "([Category]) VALUES ("; 
        sql += "'" + e.getCategory() + "') "; 
        
        //Print and Execute sql statement
        System.out.println("executing: " + sql);
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    public ArrayList<ExpenseCategory> getAllExpenseCategories() throws SQLException {
        ArrayList<ExpenseCategory> allExpenseCategories = new ArrayList<ExpenseCategory>(); 
        statement = connection.createStatement();

        // Execute the statement and store query data
        ResultSet result = statement.executeQuery("SELECT * FROM ExpenseCategory");
        while(result.next()){
         ExpenseCategory e = new ExpenseCategory();
         e.setId(result.getInt(DbConstants.EXPENSECATEGORY_ID));
         e.setCategory(result.getString(DbConstants.EXPENSECATEGORY_CATEGORY));
         allExpenseCategories.add(e);    
     }
     return allExpenseCategories;
    }

    public ExpenseCategory getExpenseCategoryById(int id) throws SQLException {
        ExpenseCategory e = new ExpenseCategory();
        //Create the statement object to connect
        statement = connection.createStatement();
        //Execute the statement and store query data
        String sql = "SELECT * FROM ExpenseCategory WHERE ID=" + id;
        System.out.println("Executing: " + sql);
        ResultSet result = statement.executeQuery(sql);
        //Alter this code, read the result set and put the data in to a new Property
        if (result.next()) {          
         e.setId(result.getInt(DbConstants.EXPENSECATEGORY_ID));
         e.setCategory(result.getString(DbConstants.EXPENSECATEGORY_CATEGORY));
        } 
        return e;
    }

    public void UpdateExpenseCategory(ExpenseCategory oldRow) throws SQLException {
        statement = connection.createStatement();
        String sql = "Update ExpenseCategory SET ";
              
        sql += "Category = " + oldRow.getCategory() + "'";
        
        sql += "WHERE ID = " + oldRow.getId(); 
        System.out.println("Executing: " + sql);
        statement.executeUpdate(sql);
    }

    public void deleteExpenseCategory(int id) throws SQLException {
        statement = connection.createStatement();
        // Execute the statement and update
        String sql = "DELETE FROM ExpenseCategory WHERE ID = " + id;
        statement.executeUpdate(sql);
    }
}