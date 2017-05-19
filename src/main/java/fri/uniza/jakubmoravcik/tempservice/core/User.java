/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.uniza.jakubmoravcik.tempservice.core;

/**
 *
 * @author jakubmoravcik
 */
import java.security.Principal;

public class User implements Principal {
    private final String meno;

//    private final Set<String> privilegia;
    private final String privilegia;

    public User(String meno) {
        this.meno = meno;
        this.privilegia = null;
    }

//    public User(String name, Set<String> roles) {
    public User(String name, String roles) {
        this.meno = name;
        this.privilegia = roles;
    }

    @Override
    public String getName() {
        return meno;
    }

    public int getId() {
        return (int) (Math.random() * 100);
    }

    public String getRoles() {
        return privilegia;
    }
}
