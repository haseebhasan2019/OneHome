package com.onehome.service;

import java.sql.SQLException;
import java.util.ArrayList;
import com.onehome.data.*;
import com.onehome.model.Users;
import com.onehome.utilities.ErrorMessages;

public class UserService {
    String message = "";
    static UserDAO userdao = new UserDAO();
    
    public UserService(){
        userdao.establishConnection();
    } 

    public String addUser(Users u) {
		String errorMessage = "";
        // errorMessage = validateUserInput(s);
        if (errorMessage.isEmpty())
        {
            try
            {
                userdao.saveUser(u);
                errorMessage = "User saved";
            }
            catch (SQLException e)
            {
                errorMessage = "Unable to add user";
            }
        }
        return errorMessage;
    }
    
	public Users searchUserByID(int id) {
        try
        {
            return userdao.getUserById(id);
        }
        catch (SQLException e)
        {
            return null;
        }
	}

	public String deleteUser(int id) {
        try
        {
            userdao.deleteUser(id);
        }
        catch (SQLException e) 
        {
            message = "Unable to delete property with ID " + id;
        }
        return message;
	}

	public String updateUser(Users newRow) {
		Users oldRow = searchUserByID(newRow.getId());
        Users updatedRow = mergeUser(oldRow, newRow);
        message = validateUserInput(updatedRow);
        
        if(message.isEmpty()){
            try 
        {
            userdao.UpdateUser(oldRow);
        }
        catch (SQLException e) 
        {
            message = "Unable to edit user info for " + oldRow.getFirstName() + " " + oldRow.getLastName(); 
        }
    }
        return message;
	}

    private Users mergeUser(Users oldRow, Users newRow) {
        if( newRow.getFirstName() != null && !newRow.getFirstName().equals(oldRow.getFirstName()))
        {
            oldRow.setFirstName((newRow.getFirstName()));
        }
        if( newRow.getLastName() != null && !newRow.getLastName().equals(oldRow.getLastName()))
        {
            oldRow.setLastName((newRow.getLastName()));
        }
        if( newRow.getEmail() != null && !newRow.getEmail().equals(oldRow.getEmail()))
        {
            oldRow.setEmail((newRow.getEmail()));
        }
        if( newRow.getUserPassword() != null && !newRow.getUserPassword().equals(oldRow.getUserPassword()))
        {
            oldRow.setUserPassword((newRow.getUserPassword()));
        }
        if(newRow.getCreatedBy() != null && !newRow.getCreatedBy().equals(oldRow.getCreatedBy()))
        {
            oldRow.setCreatedBy(newRow.getCreatedBy());
        }
        if (oldRow.getFirstName() != null && (oldRow.getFirstName().isEmpty()))
            oldRow.setFirstName(null);
        if (oldRow.getLastName() != null && (oldRow.getLastName().isEmpty()))
            oldRow.setLastName(null);
        if (oldRow.getEmail() != null && (oldRow.getEmail().isEmpty()))
            oldRow.setEmail(null);
        if (oldRow.getUserPassword() != null && (oldRow.getUserPassword().isEmpty()))
            oldRow.setUserPassword(null);
        if (oldRow.getCreatedBy() != null && (oldRow.getCreatedBy().isEmpty()))
            oldRow.setCreatedBy(null);
        return oldRow;
    }

    private String validateUserInput(Users u) {
    String message = "";
    if (u.getFirstName() != null && (u.getFirstName().isEmpty()))
        message += ErrorMessages.NULL_FIRSTNAME; 
    if (u.getLastName() != null && (u.getLastName().isEmpty()))
        message += ErrorMessages.NULL_LASTNAME;
    if (u.getEmail() != null && (u.getEmail().isEmpty()))
        message += ErrorMessages.NULL_EMAIL;
    if (u.getUserPassword() != null && (u.getUserPassword().isEmpty()))
        message += ErrorMessages.NULL_USERPASSWORD;
    return message;
    }

	public ArrayList<Users> getAllUsers() {
        try 
        {
            return userdao.getAllUsers();
        }
        catch (SQLException e) 
        {   
            return null;
        }   
	}
    

}