/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.uniza.jakubmoravcik.tempservice.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import fri.uniza.jakubmoravcik.tempservice.core.Pouzivatel;
import fri.uniza.jakubmoravcik.tempservice.core.TempSystem;
import fri.uniza.jakubmoravcik.tempservice.db.PouzivatelDAO;
import fri.uniza.jakubmoravcik.tempservice.views.LoginView;
import fri.uniza.jakubmoravcik.tempservice.views.SignupView;
import io.dropwizard.hibernate.UnitOfWork;
import java.net.URI;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

/**
 * Login resource class, which implements simple API users to login and logout 
 * @author jakubmoravcik
 */
@Path("/login")
@Produces(MediaType.TEXT_HTML)
public class LoginResource {

    private final TempSystem tempSystem;

    private final PouzivatelDAO userDataAbstractObject;
    
    
    public LoginResource(TempSystem tempSystem, PouzivatelDAO dao) {
        this.tempSystem = tempSystem;
        this.userDataAbstractObject = dao;
    }

    @GET
    @UnitOfWork
    public LoginView showLoginPage() {
        userDataAbstractObject.create(new Pouzivatel("admin","admin"));
        return new LoginView();
    }

    @Path("/signup")
    @GET
    public SignupView showSignupPage() {
        return new SignupView();
    }
    
    @Path("/signup")
    @POST
    @Timed
    @UnitOfWork
    @Produces(MediaType.TEXT_HTML)
    public Response registerUser(
            @FormParam("username") Optional<String> username,
            @FormParam("email") Optional<String> email,
            @FormParam("password") Optional<String> password
    ) {
        userDataAbstractObject.create(new Pouzivatel(username.get(), password.get(), email.get()));
        URI uri = UriBuilder.fromUri("/login").build();
        return Response.seeOther(uri).build();
    }


    @POST
    @Produces({MediaType.TEXT_HTML})
    @UnitOfWork
    public Response makeLogin(
            @FormParam("username") Optional<String> username,
            @FormParam("password") Optional<String> password) {
        List list = userDataAbstractObject.findByUsername(username.get());
        for (int i = 0; i < list.size(); i++) {
            Pouzivatel user = (Pouzivatel) list.get(i);
            if (user.getHeslo().equalsIgnoreCase(password.get())) {
                tempSystem.setPrihlasenyPouzivatel(user);
                URI uri = UriBuilder.fromUri("/temp").build();
                return Response.seeOther(uri).build();
            }
        }
        URI uri = UriBuilder.fromUri("/login").build();
        return Response.seeOther(uri).build();
    }
}
