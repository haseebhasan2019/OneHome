package com.onehome.resources;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import javax.ws.rs.*;

import com.onehome.model.Warranty;
import com.onehome.service.WarrantyService;

@Path("/warranty")
public class WarrantyResource {

    WarrantyService warrantyservice = new WarrantyService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Warranty> getWarrantyById(@QueryParam("id") int id, @QueryParam("warrantyid") int warrantyid)
    {
        if (warrantyid == 0)
        {
            ArrayList<Warranty> array = new ArrayList<>();
            Warranty a = warrantyservice.searchWarrantyByID(id);    
            array.add(a);
            return array;
        }
        else if (id == 0)
        {    
            return warrantyservice.getWarrantiesByProperty(id);
        }
        return null;
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Warranty> getAllWarranties()
    {
        return warrantyservice.getAllWarranties();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addWarranty(Warranty w)
    {
        System.out.println(w.toString());
        String message = warrantyservice.addWarranty(w);
        if (message.equals(""))
            return "Successfully Added Warranty";
        return message;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteWarrantyById(@QueryParam("id") int id)
    {
        String message = warrantyservice.deleteWarranty(id);
        if (message.equals(""))
            return "Successfully Deleted Warranty with ID " + id;
        return message;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON)
    public String editWarrantyById(Warranty newRow)
    {
        String message = warrantyservice.updateWarranty(newRow);
        System.out.println(newRow.toString());
        if (message.equals(""))
            return "Successfully Edited Warranty with ID " + newRow.getId();
        return message;
    }
   
}