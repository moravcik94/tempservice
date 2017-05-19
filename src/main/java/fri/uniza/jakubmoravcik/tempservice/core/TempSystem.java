package fri.uniza.jakubmoravcik.tempservice.core;

import java.util.ArrayList;

/**
 *  Temp system class
 * @author jakubmoravcik
 */
public class TempSystem {

    private Pouzivatel prihlasenyPouzivatel;

    private final ArrayList<Device> devices;
    private final ArrayList<String> deviceNames;

    private String deviceMessage = "Teplota zariadenia je: ";

    public String getDeviceMessage(String selectedDevice) {
        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getName().equalsIgnoreCase(selectedDevice)) {
                return devices.get(i).getMessage();
            }
        }
        return deviceMessage;
    }

    public void setDeviceMessage(String deviceMessage) {
        this.deviceMessage = deviceMessage;
    }

    public TempSystem() {
        devices = new ArrayList<>();
        deviceNames = new ArrayList<>();
    }

    public ArrayList<Device> getDevices() {
        return devices;
    }

    public ArrayList<String> getDeviceNames() {
        return deviceNames;
    }

    public Pouzivatel getPrihlasenyPouzivatel() {
        return prihlasenyPouzivatel;
    }

    public void setPrihlasenyPouzivatel(Pouzivatel prihlasenyPouzivatel) {
        this.prihlasenyPouzivatel = prihlasenyPouzivatel;
    }

    public void addDevice(Device zariadenie) {
        if (deviceNames.isEmpty()){
            deviceNames.add(zariadenie.getName());
        } else {
            if (!deviceNames.contains(zariadenie.getName())){
                deviceNames.add(zariadenie.getName());
            }
        }
        if (devices.isEmpty()){
            devices.add(zariadenie);
        } else {
            for (int i = 0; i < devices.size(); i++) {
                if (devices.get(i).getName().equalsIgnoreCase(zariadenie.getName())){
                    devices.get(i).setTemperature(zariadenie.getTemperature());
                    return;
                }
            }
            devices.add(zariadenie);
        }
    }

}
