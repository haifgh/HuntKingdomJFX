/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import entities.User;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rejeb
 */
public class UserSession {
   // private static final FosUserServices fs = new FosUserServices();
    
    private static UserSession instance = null;
    private static User user = null;
    private final Map<Integer,Integer> panier= new HashMap<>();
    private UserSession(User userConnected) {
        UserSession.user = userConnected;        
    } 

    public  Map<Integer, Integer> getPanier() {
        return panier;
    }

    public  void addToPanier(Integer idProduit,Integer qte) {
        if(panier.containsKey(idProduit)){
            panier.replace(idProduit, panier.get(idProduit)+qte);
        }
        else{
            panier.put(idProduit, qte);
        }
    }
    public  void removeFromPanier(Integer idProduit) {
        if(panier.containsKey(idProduit)){
           panier.remove(idProduit);
        }
    }
    
    
    
    public final static UserSession getInstance(User userConnected) {

        if (UserSession.instance == null) {
            UserSession.instance = new UserSession(userConnected);
        }
        return UserSession.instance;
    }

    public static UserSession getInstance() {
        return instance;
    }

    public static void setInstance(UserSession instance) {
        UserSession.instance = instance;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        UserSession.user = user;
    }

    public UserSession() {
    }
    
}
