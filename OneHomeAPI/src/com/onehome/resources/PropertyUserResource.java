package com.onehome.resources;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import javax.ws.rs.*;
import com.onehome.model.PropertyUser;
import com.onehome.service.PropertyUserService;

@Path("/propertyuser")
public class PropertyUserResource {
//http://localhost:8080/property?id=2
//http://localhost:8080/property/2

PropertyUserService propertyuserservice = new PropertyUserService();

@GET
//@Produces("application/xml")
@Produces(MediaType.APPLICATION_JSON)
//@Path("/property/{id}")
//public Property getPropertyById(@PathParam("id") Integer id){
    public PropertyUser getPropertyUserById(@QueryParam("id") String id)
    {
        return propertyuserservice.searchPropertyUserByID(id);
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<PropertyUser> getAllPropertyUsers()
    {
        return propertyuserservice.getAllPropertyUsers();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addPropertyUser(PropertyUser pu)
    {
        System.out.println(pu.toString());
        String message = propertyuserservice.addPropertyUser(pu);
        if (message.equals(""))
            return "Successfully Added PropertyUser";
        return message;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String deletePropertyUserById(@QueryParam("id") int id)
    {
        String message = propertyuserservice.deletePropertyUser(id);
        if (message.equals(""))
            return "Successfully Deleted PropertyUser with ID " + id;
        return message;
    }

    @PUT //(Update)
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON)
    public String editPropertyUserById(PropertyUser newRow)
    {
        String message = propertyuserservice.updatePropertyUser(newRow);
        //System.out.println(oldRow.toString());
        System.out.println(newRow.toString());
        if (message.equals(""))
            return "Successfully Edited Propertyuser with ID " + newRow.getPropertyId();
        return message;
    }

}