package fri.uniza.jakubmoravcik.tempservice.resources;

import fri.uniza.jakubmoravcik.tempservice.core.TempSystem;
import java.net.URI;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

/**
 * Logout resource class, which implements simple API users to logout 
 * @author jakubmoravcik
 */
@Path("/logout")
public class LogoutResource {
    
    TempSystem tempSystem;
    
    public LogoutResource(TempSystem tempSystem) {
        this.tempSystem = tempSystem;
    }
    
    public LogoutResource() {
    }
    
    @POST
    public Response makeLogout() {
        tempSystem.setPrihlasenyPouzivatel(null);
        URI uri = UriBuilder.fromUri("/login").build();
        return Response.seeOther(uri).build();
    }
}
