package fri.uniza.jakubmoravcik.tempservice.resources;

import com.codahale.metrics.annotation.Timed;
import fri.uniza.jakubmoravcik.tempservice.core.TempSystem;
import fri.uniza.jakubmoravcik.tempservice.views.DeviceView;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jakubmoravcik
 */
@Path("/temp")
@Produces(MediaType.TEXT_HTML)
public class TempResource {

    TempSystem tempSystem;

    public TempResource(TempSystem tempSystem) {
        this.tempSystem = tempSystem;

    }

    public TempResource() {
    }

    @GET
    @Timed
    @Produces(MediaType.TEXT_HTML)
    public DeviceView showDevicePage() {
        return new DeviceView(tempSystem.getPrihlasenyPouzivatel(), tempSystem.getDeviceNames());
    }

}
