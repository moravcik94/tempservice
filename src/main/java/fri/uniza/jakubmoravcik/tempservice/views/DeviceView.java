package fri.uniza.jakubmoravcik.tempservice.views;

import fri.uniza.jakubmoravcik.tempservice.core.Pouzivatel;
import io.dropwizard.views.View;
import java.util.ArrayList;

/**
 *
 * @author jakubmoravcik
 */
public class DeviceView extends View {

    private String temp = "0";

    private String currentUser;
    private String selectedDevice;
    private String message;

    ArrayList<String> items;

    public ArrayList<String> getItems() {
        return items;
    }


    
    

//    public DeviceView(Pouzivatel currentUser, List<Device> list) {
    public DeviceView(Pouzivatel currentUser, ArrayList<String> items) {

        super("devices.mustache");
        this.currentUser = currentUser.getMeno();
        this.items = items;

    }

    public DeviceView(Pouzivatel currentUser, ArrayList<String> deviceNames, String selectedDevice, String message) {
        super("devices.mustache");
        this.currentUser = currentUser.getMeno();
        this.selectedDevice = selectedDevice;
        this.message = message;
        items = deviceNames;
    }

    public DeviceView() {
        super("devices.mustache");
    }

    public String getTemp() {
        return temp;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public String getSelectedDevice() {
        return selectedDevice;
    }

    public String getMessage() {
        return message;
    }
    
    

}
