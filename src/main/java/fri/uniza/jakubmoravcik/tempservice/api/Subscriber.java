package fri.uniza.jakubmoravcik.tempservice.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

/** Model class
 *
 * @author jakubmoravcik
 */
public class Subscriber {

    private String deviceName;
    private String temperature;
    
    /**
     * Consturcor
     */
    public Subscriber() {
        // Jackson deserialization
    }

    @JsonProperty
    public String getDeviceName() {
        return deviceName;
    }

    @JsonProperty
    public String getTemperature() {
        return temperature;
    }

    public Subscriber(String deviceName, String temperature) {
        this.deviceName = deviceName;
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", deviceName)
                .add("id", temperature)
                .toString();
    }

}
