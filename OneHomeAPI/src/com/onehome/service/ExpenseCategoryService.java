package com.onehome.service;

import com.onehome.data.*;
import com.onehome.model.ExpenseCategory;
import com.onehome.utilities.ErrorMessages;

import java.sql.SQLException;
import java.util.ArrayList;

public class ExpenseCategoryService {
    String message = "";
    static ExpenseCategoryDAO expensecategorydao = new ExpenseCategoryDAO(); 

    public ExpenseCategoryService() 
    {
        expensecategorydao.establishConnection();
    }

    public ArrayList<ExpenseCategory> getAllExpenseCategories()
    {
        try 
        {
            return expensecategorydao.getAllExpenseCategories();  
        }
        catch (SQLException e) 
        {   
            return null;
        }    
    }

    public String addExpenseCategory(ExpenseCategory c) 
    {
        
        String errorMessage = "";
        // errorMessage = validateUserInput(s);
        if (errorMessage.isEmpty())
        {
            try
            {
                expensecategorydao.saveExpenseCategory(c);
                errorMessage = "Property saved";
            }
            catch (SQLException e)
            {
                errorMessage = "Unable to add category for expense";
            }
        }
        return errorMessage;
    }

    public String updateExpenseCategory(ExpenseCategory newRow){
        
        ExpenseCategory oldRow = searchExpenseCategoryByID(newRow.getId());
        ExpenseCategory updatedRow = mergeExpenseCategories(oldRow, newRow);
        message = validateUserInput(updatedRow);
        
        if(message.isEmpty()){
        try 
        {
            expensecategorydao.UpdateExpenseCategory(oldRow);
        }
        catch (SQLException e) 
        {
            message = "Unable to edit expense category with ID " + oldRow.getId();
        }
    }
        return message;
    }

    private String validateUserInput(ExpenseCategory c){
        String message = "";
        if (c.getCategory() == null || c.getCategory().isEmpty() )
        {
            message += ErrorMessages.NULL_CATEGORY;
        }
        return message; 
    }

    public ExpenseCategory mergeExpenseCategories(ExpenseCategory oldRow, ExpenseCategory newRow){
        if( newRow.getCategory() != null && !newRow.getCategory().equals(oldRow.getCategory()))
        {
            oldRow.setCategory((newRow.getCategory()));
        }
       
        
        // if "" is entered change to null
        if (oldRow.getCategory() != null && (oldRow.getCategory().isEmpty()))
            oldRow.setCategory(null);
        
        return oldRow;
    }

    public String deleteExpenseCategory(int id) 
    {
        try
        {
            expensecategorydao.deleteExpenseCategory(id);
        }
        catch (SQLException e) 
        {
            message = "Unable to delete category within ExpenseCategory with ID " + id;
        }
        return message;
    }

    public ExpenseCategory searchExpenseCategoryByID(int id) 
    {
        try
        {
            return expensecategorydao.getExpenseCategoryById(id);
        }
        catch (SQLException e)
        {
            return null;
        }
	}
}