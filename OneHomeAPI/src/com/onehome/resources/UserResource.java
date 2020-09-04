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
import com.onehome.model.Users;
import com.onehome.service.*; 

@Path("/user")

public class UserResource {
    UserService userService = new UserService(); 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Users getUserbyId(@QueryParam("id") int id){
        Users u = userService.searchUserByID(id);
        return u;
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Users> getAllUser(){
        return userService.getAllUsers();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addService(Users u){
       
        //System.out.println(p.toString());
        return userService.addUser(u); 
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUserById(@QueryParam("id") int id){
        String message = userService.deleteUser(id);; 
        if (message.equals(""))
            return "Succesfully deleted user with id " + id;
        return message;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editUserById(Users u){
        String message = userService.updateUser(u); 
        return message;
    }
}
