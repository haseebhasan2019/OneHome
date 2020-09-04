package com.onehome.resources;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import javax.ws.rs.*;

import com.onehome.model.Expense;
import com.onehome.service.ExpenseService;

@Path("/expense")
public class ExpenseResource {
    ExpenseService expenseservice = new ExpenseService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Expense> getExpenseById(@QueryParam("id") int id)
    {
        ArrayList<Expense> array = new ArrayList<>();
        Expense e = expenseservice.searchExpenseByID(id);
        array.add(e);
        return array;
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Expense> getAllExpenses()
    {
        return expenseservice.getAllExpenses();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addExpense(Expense e)
    {
        System.out.println(e.toString());
        String message = expenseservice.addExpense(e);
        if (message.equals(""))
            return "Successfully Added Expense";
        return message;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteExpenseById(@QueryParam("id") int id)
    {
        String message = expenseservice.deleteExpense(id);
        if (message.equals(""))
            return "Successfully Deleted Expense with ID " + id;
        return message;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON)
    public String editExpenseById(Expense newRow)
    {
        String message = expenseservice.updateExpense(newRow);
        System.out.println(newRow.toString());
        if (message.equals(""))
            return "Successfully Edited Expense with ID " + newRow.getId();
        return message;
    }
    
}