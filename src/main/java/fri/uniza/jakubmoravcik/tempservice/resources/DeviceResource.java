package fri.uniza.jakubmoravcik.tempservice.resources;

import fri.uniza.jakubmoravcik.tempservice.api.Subscriber;
import fri.uniza.jakubmoravcik.tempservice.core.Device;
import fri.uniza.jakubmoravcik.tempservice.core.TempSystem;
import fri.uniza.jakubmoravcik.tempservice.views.DeviceView;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Device resource class, which implements simple API for each device 
 * @author jakubmoravcik
 */
@Path("/device")
@Produces(MediaType.APPLICATION_JSON)
public class DeviceResource {
    
    private TempSystem tempSystem;    

    public DeviceResource(TempSystem tempSystem) {
        this.tempSystem = tempSystem;
    }

    public DeviceResource() {
    }
    
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("ADMIN")
    public Subscriber addDevice(Subscriber sub){
        tempSystem.addDevice(new Device(sub.getDeviceName(), Double.parseDouble(sub.getTemperature())));
        return sub;
    }
    
    @Path("/{deviceName}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public DeviceView sayHello(@PathParam("deviceName") String selectedDevice){
        return new DeviceView(tempSystem.getPrihlasenyPouzivatel(),tempSystem.getDeviceNames(), selectedDevice, tempSystem.getDeviceMessage(selectedDevice));
    }
    
}
