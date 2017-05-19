package fri.uniza.jakubmoravcik.tempservice;

import fri.uniza.jakubmoravcik.tempservice.auth.DeviceAuthenticator;
import fri.uniza.jakubmoravcik.tempservice.core.Pouzivatel;
import fri.uniza.jakubmoravcik.tempservice.core.TempSystem;
import fri.uniza.jakubmoravcik.tempservice.core.User;
import fri.uniza.jakubmoravcik.tempservice.db.PouzivatelDAO;
import fri.uniza.jakubmoravcik.tempservice.resources.DeviceResource;
import fri.uniza.jakubmoravcik.tempservice.resources.LoginResource;
import fri.uniza.jakubmoravcik.tempservice.resources.LogoutResource;
import fri.uniza.jakubmoravcik.tempservice.resources.TempResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

/**
 * Application class which pulls together the various bundles and commands which
 * provide basic functionality
 *
 * @author jakubmoravcik
 */
public class TempApplication extends Application<TempServiceConfiguration> {

    private TempSystem tempSystem;

    /*
    Hibernate configuration instance
     */
    private final HibernateBundle<TempServiceConfiguration> hibernate = new HibernateBundle<TempServiceConfiguration>(Pouzivatel.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(TempServiceConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    /**
     * Main function
     * @param args args
     * @throws Exception chyba
     */
    public static void main(String[] args) throws Exception {
        new TempApplication().run(args);
    }

    /**
     * initialize method is used to configure aspects of the application
     * required before the application is run, like bundles, configuration
     * source providers, etc.
     *
     * @param bootstrap bootstarp
     */
    @Override
    public void initialize(Bootstrap<TempServiceConfiguration> bootstrap) {

        bootstrap.addBundle(new ViewBundle<>());
        bootstrap.addBundle(new AssetsBundle());
        bootstrap.addBundle(hibernate);
    }

    /**
     * Run method
     *
     * @param tempConfig application configuration type
     * @param enviroment current enviromet of application
     * @throws Exception chyba
     */
    @Override
    public void run(TempServiceConfiguration tempConfig, Environment enviroment) throws Exception {
        tempSystem = new TempSystem();
        final PouzivatelDAO userDAO = new PouzivatelDAO(hibernate.getSessionFactory());
        enviroment.jersey().register(new LoginResource(tempSystem, userDAO));
        enviroment.jersey().register(new TempResource(tempSystem));
        enviroment.jersey().register(new LogoutResource(tempSystem));
        enviroment.jersey().register(new DeviceResource(tempSystem));
        enviroment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new DeviceAuthenticator())
                .setRealm("SUPER SECRET STUFF")
                .buildAuthFilter()));
        enviroment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));

    }

}
