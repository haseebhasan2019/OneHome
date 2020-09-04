import java.net.URI;
import javax.ws.rs.core.UriBuilder;

import com.onehome.resources.*;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class StartWebServer {
    public static void main(String[] args){
        //Step 1: Create an object of Server
        //Step 2: Set the base URI 
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(8080).build();
        //Step 3: Activate jersey config
        ResourceConfig config =  new ResourceConfig(PropertyResource.class);
        config.register(ApplianceResource.class);
        config.register(ExpenseResource.class);
        config.register(ExpenseCategoryResource.class);
        config.register(PropertyUserResource.class);
        config.register(ServiceResource.class);
        config.register(UserResource.class);
        config.register(VendorResource.class);
        config.register(WarrantyResource.class);

        //Step 4: Start web server      
        Server server = JettyHttpContainerFactory.createServer(baseUri, config);
        try{
            server.start();
        }
        catch(Exception e){
            System.out.println("Unable to start web server");
        }
    }
    //URI (Uniform Resource Identifier) - http://localhost:8080/property?id=3
    //URL (Uniform Resource Locator) - http://localhost:8080/property
    //URN (Uniform Resource Name) - localhost:8080/property?id=3
}