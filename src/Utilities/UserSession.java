
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package Utilities;

import java.util.HashMap;
import java.util.Map;

import Models.User;

/**
 *
 * @author rejeb
 */
public class UserSession {

    // private static final FosUserServices fs = new FosUserServices();
    private static UserSession          instance = null;
    private User                        user     = null;
    private final Map<Integer, Integer> panier   = new HashMap<>();

    public UserSession() {}

    private UserSession(User userConnected) {
        this.user = userConnected;
    }

    public void addToPanier(Integer idProduit, Integer qte) {
        if (panier.containsKey(idProduit)) {
            panier.replace(idProduit, panier.get(idProduit) + qte);
        } else {
            panier.put(idProduit, qte);
        }
    }

    public void removeFromPanier(Integer idProduit) {
        if (panier.containsKey(idProduit)) {
            panier.remove(idProduit);
        }
    }

    public static UserSession getInstance() {
        return instance;
    }

    public final static UserSession getInstance(User userConnected) {
        if (UserSession.instance == null) {
            UserSession.instance = new UserSession(userConnected);
        }

        return UserSession.instance;
    }

    public static void setInstance(UserSession instance) {
        UserSession.instance = instance;
    }

    public Map<Integer, Integer> getPanier() {
        return panier;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static class getInstance {
        public getInstance() {}
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
