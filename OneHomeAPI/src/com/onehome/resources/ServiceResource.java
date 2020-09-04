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
import com.onehome.model.Service;
import com.onehome.service.*; 

@Path("/service")
public class ServiceResource {
    ServiceService serviceService = new ServiceService(); 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Service getServicebyId(@QueryParam("id") int id){
        Service s = serviceService.searchServiceByID(id);
        return s;
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Service> getAllService(){
        return serviceService.getAllServices();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addService(Service s){
       
        //System.out.println(p.toString());
        return serviceService.addService(s); 
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteServiceById(@QueryParam("id") int id){
        String message = serviceService.deleteService(id);; 
        if (message.equals(""))
            return "Succesfully deleted service with id " + id;
        return message;
        //return propertyService.deleteProperty(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editServiceById(Service newRow){
        String message = serviceService.updateService(newRow); 
        return message;
    }
}




   
    
   

   