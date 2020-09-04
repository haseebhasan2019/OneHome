package com.onehome.data;
import java.sql.*;
import java.util.ArrayList;
import com.onehome.model.Expense;

public class ExpenseDAO {
    Connection connection;
    Statement statement;
    String url = "jdbc:sqlserver://FAMILYROOM-PC;databaseName=OneHomeDB;user=sa;password=adpadp10";

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

    public Expense getExpenseById(int id) throws SQLException
    {
		Expense e = new Expense();
        statement = connection.createStatement();
        String sql = "SELECT * FROM Expense WHERE ID = " + id;
        System.out.println("Executing: " + sql);
        ResultSet result = statement.executeQuery(sql);
        if (result.next()) {
            e.setId(result.getInt(DbConstants.EXPENSE_ID));
            e.setPropertyId(result.getString(DbConstants.EXPENSE_PROPERTYID));
            e.setVendorId(result.getString(DbConstants.EXPENSE_VENDORID));
            e.setExpenseCategoryId(result.getString(DbConstants.EXPENSE_EXPENSECATEGORYID));
            e.setAmount(result.getString(DbConstants.EXPENSE_AMOUNT));
            e.setDateOfExpense(result.getString(DbConstants.EXPENSE_DATEOFEXPENSE));
            e.setRecurring(result.getBoolean(DbConstants.EXPENSE_ISRECURING));
            e.setNotes(result.getString(DbConstants.EXPENSE_NOTES));
            e.setCreatedOn(result.getString(DbConstants.EXPENSE_CREATEDON));
            e.setCreatedBy(result.getString(DbConstants.EXPENSE_CREATEDBY));
        } 
        return e;
	}

    public ArrayList<Expense> getAllExpenses() throws SQLException
    {
		ArrayList<Expense> expenses = new ArrayList<>();
        statement = connection.createStatement();
        String sql = "SELECT * FROM Expense";
        System.out.println("Executing: " + sql);
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            Expense e = new Expense();
            e.setId(result.getInt(DbConstants.EXPENSE_ID));
            e.setPropertyId(result.getString(DbConstants.EXPENSE_PROPERTYID));
            e.setVendorId(result.getString(DbConstants.EXPENSE_VENDORID));
            e.setExpenseCategoryId(result.getString(DbConstants.EXPENSE_EXPENSECATEGORYID));
            e.setAmount(result.getString(DbConstants.EXPENSE_AMOUNT));
            e.setDateOfExpense(result.getString(DbConstants.EXPENSE_DATEOFEXPENSE));
            e.setRecurring(result.getBoolean(DbConstants.EXPENSE_ISRECURING));
            e.setNotes(result.getString(DbConstants.EXPENSE_NOTES));
            e.setCreatedOn(result.getString(DbConstants.EXPENSE_CREATEDON));
            e.setCreatedBy(result.getString(DbConstants.EXPENSE_CREATEDBY));
            expenses.add(e);
        }
        return expenses;
	}

    public void saveExpense(Expense e) throws SQLException 
    {
        String sql = "INSERT INTO Expense ([PropertyID],[VendorID],[ExpenseCategoryID],[Amount],[DateofExpense],[IsRecurring],[Notes],[CreatedOn],[CreatedBy]) VALUES("; 
        sql += "" + e.getPropertyId() + ", "; 
        sql += "" + e.getVendorId() + ", "; 
        sql += "" + e.getExpenseCategoryId() + ", "; 
        sql += "" + e.getAmount() + ", "; 
        sql += "'" + e.getDateOfExpense() + "', "; 

        if (e.isRecurring() == true) 
            sql += "" + 1 + ", ";
        else
            sql += "" + 0 + ", ";
        
        if (e.getNotes() == null || e.getNotes().equals("null"))
            sql += "null, ";
        else
            sql += "'" + e.getNotes() + "', ";
        
        sql += "getDate(), ";
        
        if (e.getCreatedBy() == null || e.getCreatedBy().equals("null"))
            sql += "null, ";
        else
            sql += "'" + e.getCreatedBy() + "', "; 
       
        //Print and Execute sql statement
        System.out.println("Executing: " + sql);
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    public void deleteExpense(int id) throws SQLException
    {
        String sql = "DELETE FROM Expense WHERE ID = " + id;
        System.out.println(sql);
        statement = connection.createStatement();
        statement.executeUpdate(sql);
	}
    
    public void UpdateExpense(Expense e) throws SQLException
    {
        statement = connection.createStatement();
        String sql = "Update Expense SET ";
        sql += "PropertyID = " + e.getPropertyId() + ", ";
        sql += "VendorID = " + e.getVendorId() + ", ";
        sql += "ExpenseCategoryID = " + e.getExpenseCategoryId() + ", "; 
        sql += "Amount = " + e.getAmount() + ", "; 
        sql += "DateofExpense = '" + e.getDateOfExpense() + "', "; 
        
        if (e.isRecurring())
            sql += "IsRecurring = 1,";
        else
            sql += "IsRecurring = 0,";

        if (e.getNotes() == null || e.getNotes().equals("null"))
            sql += "Notes = null, ";
        else
            sql += "Notes = '" + e.getNotes() + "', ";

        if (e.getCreatedBy() == null || e.getCreatedBy().equals("null"))
            sql += "null ";
        else
            sql += "CreatedBy = '" + e.getCreatedBy() + "' ";

        sql += "WHERE Id = " + e.getId();

        System.out.println("Executing: " + sql);
        statement.executeUpdate(sql);
	}
}