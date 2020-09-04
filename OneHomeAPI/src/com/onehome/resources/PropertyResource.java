package com.onehome.resources;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import javax.ws.rs.*;
import com.onehome.model.*;
import com.onehome.service.PropertyService;

@Path("/property")
public class PropertyResource {
//http://localhost:8080/property?id=2
//http://localhost:8080/property/2
    Users u = new Users(); 
    PropertyService propertyservice = new PropertyService();

    @GET
    //@Produces("application/xml")
    @Produces(MediaType.APPLICATION_JSON)
    //@Path("/property/{id}")
    //public Property getPropertyById(@PathParam("id") Integer id){
    public Property getPropertyById(@QueryParam("id") int id)
    {
        return propertyservice.searchPropertyByID(id);
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Property> getAllProperties()
    {
        return propertyservice.getAllProperties();
    }

    @GET
    @Path("/propertyuser")
    @Produces(MediaType.APPLICATION_JSON)
    public void getPropertyUsers(@QueryParam("id") int id){

    }
    
    @GET
    @Path("/propappl")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Appliance> getPropertyAppliances(@QueryParam("id") int id){
        return propertyservice.getAllPropertyAppliances(id);
    }
    
    @GET
    @Path("/propserv")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Service> getPropertyServices(@QueryParam("id") int id){
        return propertyservice.getAllPropertyServices(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addProperty(Property p)
    {
        System.out.println(p.toString());
        String message = propertyservice.addProperty(p);
        if (message.equals(""))
            return "Successfully Added Property";
        return message;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String deletePropertyById(@QueryParam("id") int id)
    {
        String message = propertyservice.deleteProperty(id);
        if (message.equals(""))
            return "Successfully Deleted Property with ID " + id;
        return message;
    }

    @PUT //(Update)
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON)
    public String editPropertyById(Property newRow)
    {
        String message = propertyservice.updateProperty(newRow);
        //System.out.println(oldRow.toString());
        System.out.println(newRow.toString());
        if (message.equals(""))
            return "Successfully Edited Property with ID " + newRow.getId();
        return message;
    }
}