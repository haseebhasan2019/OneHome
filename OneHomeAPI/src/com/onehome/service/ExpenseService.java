package com.onehome.service;

import com.onehome.model.Expense;
import com.onehome.data.ExpenseDAO;
import com.onehome.utilities.*;

import java.sql.*;
import java.util.*;

public class ExpenseService {

    static ExpenseDAO expensedao = new ExpenseDAO();

    //Default Constructor
    public ExpenseService() {
        expensedao.establishConnection();
    }

    public Expense searchExpenseByID(int id) {
		try {
            return expensedao.getExpenseById(id);
        } catch (SQLException e) {
            return null;
        }
	}

	public ArrayList<Expense> getAllExpenses() {
		try {
            return expensedao.getAllExpenses();
        } catch (SQLException e) {
            return null;
        }
    }
    
    public String addExpense(Expense ex) 
    {
		String message = validateUserInput(ex);
        if (message.isEmpty()) 
        {
            try {
                expensedao.saveExpense(ex);
            } catch (SQLException e) {
                message = "Unable to add Expense";
            }
        }
        return message;
    }

    private String validateUserInput(Expense e) 
    {
        //Required: PropertyID, VendorID, ExpenseCategory, Amount, DateofExpense
        String message = "";
        if (e.getPropertyId() == null || e.getPropertyId().isEmpty())
            message += ErrorMessages.PROPERTYID_NULL_ERROR;
        if (e.getVendorId() == null || e.getVendorId().isEmpty())
            message += ErrorMessages.VENDORID_NULL_ERROR;
        if (e.getExpenseCategoryId() == null || e.getExpenseCategoryId().isEmpty())
            message += ErrorMessages.EXPENSECATEGORY_NULL_ERROR;
        if (e.getAmount() == null || e.getAmount().isEmpty())
            message += ErrorMessages.AMOUNT_NULL_ERROR;
        if (e.getDateOfExpense() == null || e.getDateOfExpense().isEmpty())
            message += ErrorMessages.DATEOFEXPENSE_NULL_ERROR;
        
        //Validate: PropertyID, VendorID, ExpenseCategory, Amount are numeric & DateofExpense is a date
        if (e.getPropertyId() != null && !e.getPropertyId().isEmpty()){
            try {
                Integer.parseInt(e.getPropertyId());
            } catch (NumberFormatException n) {
                message += ErrorMessages.PROPERTYID_TYPE_ERROR;
            }
        }
        if (e.getVendorId() != null && !e.getVendorId().isEmpty()){
            try {
                Integer.parseInt(e.getVendorId());
            } catch (NumberFormatException n) {
                message += ErrorMessages.VENDORID_TYPE_ERROR;
            }
        }
        if (e.getExpenseCategoryId() != null && !e.getExpenseCategoryId().isEmpty()){
            try {
                Integer.parseInt(e.getExpenseCategoryId());
            } catch (NumberFormatException n) {
                message += ErrorMessages.EXPENSECATEGORY_TYPE_ERROR;
            }
        }
        if (e.getAmount() != null && !e.getAmount().isEmpty()){
            try {
                Integer.parseInt(e.getAmount());
            } catch (NumberFormatException n) {
                message += ErrorMessages.AMOUNT_TYPE_ERROR;
            }
        }

        if ((e.getDateOfExpense() != null && !e.getDateOfExpense().isEmpty()))
            if (!DateUtil.isDateValid(e.getDateOfExpense()))
                message += ErrorMessages.DATEOFEXPENSE_FORMAT_ERROR;
            else
                e.setDateOfExpense(DateUtil.reformat(e.getDateOfExpense(), DateUtil.DATEFORMAT_MM_DD_YYYY, DateUtil.DATEFORMAT_YYYYMMDD));

        return message;
    }
    
    public String deleteExpense(int id) 
    {
		String message = "";
        try {
            expensedao.deleteExpense(id);
        } catch (SQLException e) {
            message = "Unable to delete Expense with ID " + id;
        }
        return message;
    }
    
    public String updateExpense(Expense newRow) 
    {
		Expense oldRow = searchExpenseByID(newRow.getId());
        mergeExpenses(oldRow, newRow);
        String message = validateUserInput(oldRow);

        if (message.isEmpty())
        {
            try {
                expensedao.UpdateExpense(oldRow);
            } catch (SQLException e) {
                message = "Unable to update Expense with ID " + oldRow.getId();
            } 
        }
        return message;
    }
    
    private void mergeExpenses(Expense oldRow, Expense newRow) 
    {
        if ( (newRow.getPropertyId() != null) && (!newRow.getPropertyId().equals(oldRow.getPropertyId())) )
            oldRow.setPropertyId(newRow.getPropertyId());            
        if ( (newRow.getVendorId() != null) && (!newRow.getVendorId().equals(oldRow.getVendorId())))
            oldRow.setVendorId(newRow.getVendorId());
        if ( (newRow.getExpenseCategoryId() != null) && (!newRow.getExpenseCategoryId().equals(oldRow.getExpenseCategoryId())))
            oldRow.setExpenseCategoryId(newRow.getExpenseCategoryId());
        if ( (newRow.getAmount() != null) && (!newRow.getAmount().equals(oldRow.getAmount())))
            oldRow.setAmount(newRow.getAmount());
        if ( (newRow.getDateOfExpense() != null) && (!newRow.getDateOfExpense().equals(oldRow.getDateOfExpense())))
            oldRow.setDateOfExpense(newRow.getDateOfExpense());
        else if ((newRow.getDateOfExpense() != null) && newRow.getDateOfExpense().isEmpty())
            oldRow.setDateOfExpense(newRow.getDateOfExpense());
        else
            oldRow.setDateOfExpense(DateUtil.reformat(oldRow.getDateOfExpense(), DateUtil.DATEFORMAT_YYYYMMDD, DateUtil.DATEFORMAT_MM_DD_YYYY));
        if (newRow.isRecurring() != oldRow.isRecurring())
            oldRow.setRecurring(newRow.isRecurring());
        if ( (newRow.getNotes() != null) && (!newRow.getNotes().equals(oldRow.getNotes())))
            oldRow.setNotes(newRow.getNotes());
        if ( (newRow.getCreatedBy() != null) && (!newRow.getCreatedBy().equals(oldRow.getCreatedBy())))
            oldRow.setCreatedBy(newRow.getCreatedBy());

        //Change all "" fields to nulls before validating
        if ( oldRow.getPropertyId() != null && (oldRow.getPropertyId().isEmpty()))
            oldRow.setPropertyId(null);            
        if ( oldRow.getVendorId() != null && (oldRow.getVendorId().isEmpty()))
            oldRow.setVendorId(null);
        if ( oldRow.getExpenseCategoryId() != null && (oldRow.getExpenseCategoryId().isEmpty()))
            oldRow.setExpenseCategoryId(null);
        if ( oldRow.getAmount() != null && (oldRow.getAmount().isEmpty()))
            oldRow.setAmount(null);
        if ( oldRow.getDateOfExpense() != null && (oldRow.getDateOfExpense().isEmpty()))
            oldRow.setDateOfExpense(null);
        if ( oldRow.getNotes() != null && (oldRow.getNotes().isEmpty()))
            oldRow.setNotes(null);
        if ( oldRow.getCreatedBy() != null && (oldRow.getCreatedBy().isEmpty()))
            oldRow.setCreatedBy(null);
    }
    
}