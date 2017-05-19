package fri.uniza.jakubmoravcik.tempservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** Configuration class which specifies environment-specific parameters
 *  
 * @author jakubmoravcik
 */
public class TempServiceConfiguration extends Configuration {

    /**
     * A factory used to connect to a relational database management system.
     * Factories are used by Dropwizard to group together related configuration
     * parameters such as database connection driver, URI, password etc.
     */
    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    /**
     * A getter for the database factory.
     *
     * @return An instance of database factory deserialized from the
     * configuration file passed as a command-line argument to the application.
     */
    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
    
    /**
     * A setter for the database factory.
     * @param database parameter databazy
     */
    public void setDatabase(DataSourceFactory database) {
        this.database = database;
    }
}
