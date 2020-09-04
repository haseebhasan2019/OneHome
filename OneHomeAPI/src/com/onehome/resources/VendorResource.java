package com.onehome.resources;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import javax.ws.rs.*;

import com.onehome.model.Vendor;
import com.onehome.service.VendorService;

@Path("/vendor")
public class VendorResource 
{
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Vendor getVendorById(@QueryParam("id") int id)
    {
        VendorService propertyService = new VendorService();
        Vendor v = propertyService.searchVendorByID(id);
        return v;
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Vendor> getAllVendors()
    {
        VendorService propertyService = new VendorService();
        return propertyService.getAllVendors();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addProperty(Vendor v)
    {    
        VendorService vendorService = new VendorService();
        return vendorService.addVendor(v);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteVendorById(@QueryParam("id") int id)
    {
        VendorService vendorService = new VendorService();
        String message = vendorService.deleteVendor(id);
        if (message.equals(""))
            return "Succesfully deleted Vendor with id " + id;
        return message;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editVendorById(Vendor newRow)
    {
        VendorService vendorService = new VendorService();
        String message = vendorService.updateVendor(newRow);
        if (message.equals(""))
            return "Succesfully updated Vendor with id " + newRow.getId();
        return message;
    }

}