package fri.uniza.jakubmoravcik.tempservice.core;

/**
 * Simple Device class which represends a device instance, with two atributes,
 * name and temperature
 *
 * @author jakubmoravcik
 */
public class Device {

    private String name;

    private double temperature;
    private double minTemperature;
    private double maxTemperature;

    private final String message;

    /**
    * Simple method which returns a formated message
    * @return returns formated message
    */
    public String getMessage() {
        return message + temperature + " MIN: " + minTemperature + " MAX: " + maxTemperature;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;

        if (temperature > maxTemperature) {
            maxTemperature = temperature;
        }
        if (temperature < minTemperature) {
            minTemperature = temperature;
        }
    }

    public Device(String deviceName, Double temp) {
        this.message = "Teplota zariadenia je: ";
        name = deviceName;
        temperature = temp;
        minTemperature = 100;
        maxTemperature = 0;
    }

    public Device() {
        this.message = "Teplota zariadenia je: ";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
