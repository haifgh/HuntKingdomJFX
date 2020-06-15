/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JAIDI
 */
public class Connexion {
    private static String url= "jdbc:mysql://localhost:3306/pidevv";
    private static String usr="root";
    private static String pwd="";

    private   Connection cnx;

    public Connection getCnx() {
        return cnx;
    }
    
    private static Connexion inst;
    
    private Connexion() {
          try {
              cnx = DriverManager.getConnection(url, usr, pwd);
              System.out.println("connected to db");
          } catch (SQLException ex) {
              Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    
    public static Connexion getInstance(){
        if(inst == null) inst = new Connexion();
        return inst;
    }
    
}
