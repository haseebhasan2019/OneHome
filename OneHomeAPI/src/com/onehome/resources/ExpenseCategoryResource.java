package com.onehome.resources;
import javax.ws.rs.Path;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.onehome.model.ExpenseCategory;
import com.onehome.service.*; 

@Path("/expensecategory")
public class ExpenseCategoryResource {
    ExpenseCategoryService expenseCategoryService = new ExpenseCategoryService(); 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ExpenseCategory getExpenseCategorybyId(@QueryParam("id") int id){
        ExpenseCategory c = expenseCategoryService.searchExpenseCategoryByID(id);
        return c;
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<ExpenseCategory> getAllExpenseCategory()
    {
        return expenseCategoryService.getAllExpenseCategories();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addExpenseCatagory(ExpenseCategory c)
    {
        return expenseCategoryService.addExpenseCategory(c); 
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteExpenseCategoryById(@QueryParam("id") int id){
        String message = expenseCategoryService.deleteExpenseCategory(id);
        if (message.equals(""))
            return "Succesfully deleted category with ExpenseCatagory with id " + id;
        return message;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editExpenseCatagoryById(ExpenseCategory newRow){
        String message = expenseCategoryService.updateExpenseCategory(newRow); 
        return message;
    }
}