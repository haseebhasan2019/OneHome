package com.onehome.resources;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import javax.ws.rs.*;

import com.onehome.model.Appliance;
import com.onehome.service.ApplianceService;

@Path("/appliance")
public class ApplianceResource {

    ApplianceService applianceservice = new ApplianceService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Appliance> getApplianceById(@QueryParam("id") int id)//, @QueryParam("propertyid") int propertyId)
    {
        //if (propertyId == 0)
        {
            ArrayList<Appliance> array = new ArrayList<>();
            Appliance a = applianceservice.searchApplianceByID(id);
            array.add(a);
            return array;
        }
        // else if (id == 0)
        //     return applianceservice.getAppliancesByProperty(propertyId);
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Appliance> getAllAppliances()
    {
        return applianceservice.getAllAppliances();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addAppliance(Appliance p)
    {
        System.out.println(p.toString());
        String message = applianceservice.addAppliance(p);
        if (message.equals(""))
            return "Successfully Added Appliance";
        return message;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteApplianceById(@QueryParam("id") int id)
    {
        String message = applianceservice.deleteAppliance(id);
        if (message.equals(""))
            return "Successfully Deleted Appliance with ID " + id;
        return message;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON)
    public String editApplianceById(Appliance newRow)
    {
        String message = applianceservice.updateAppliance(newRow);
        System.out.println(newRow.toString());
        if (message.equals(""))
            return "Successfully Edited Appliance with ID " + newRow.getId();
        return message;
    }

    
}