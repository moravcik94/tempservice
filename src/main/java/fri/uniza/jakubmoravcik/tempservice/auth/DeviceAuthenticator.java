package fri.uniza.jakubmoravcik.tempservice.auth;

/**
 *
 * @author jakubmoravcik
 */
import fri.uniza.jakubmoravcik.tempservice.core.User;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import java.util.HashMap;

import java.util.Map;
import java.util.Optional;

/**
* Class which implements basic authentification
* 
*/
public class DeviceAuthenticator implements Authenticator<BasicCredentials, User> {

    private final Map validUsers;

    /**
     * Valid users with mapping user to roles
     */
    public DeviceAuthenticator() {
        validUsers = new HashMap();
        validUsers.put("admin", "ADMIN");
    }
    
    /** implementacia metody, ktora zabezpecuje porovnanie ziskanych credentials s predom zadanymi hodnotami
     *
     * @param credentials contains a authentication data
     * @return Optional of User
     * @throws io.dropwizard.auth.AuthenticationException chyba
     */
    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if (validUsers.containsKey(credentials.getUsername()) && "secret".equals(credentials.getPassword())) {
            User uzo = new User(credentials.getUsername(), (String) validUsers.get(credentials.getUsername()));
            return Optional.of(uzo);
        }
        return Optional.empty();
    }
}
