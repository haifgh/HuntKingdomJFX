/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;



import Models.Categorie;
import Utilities.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 *
 * @author arij
 */
public class CategorieServices {
     Connection conn;

    public CategorieServices() {
        conn=Connexion.getInstance().getCnx();
    }

   
    public void createC(Categorie c) {
        try {
            Statement st;
            st = conn.createStatement();
            String req="insert into categorie values ("+c.getId()+",'"+c.getNom()+"')";
            st.executeUpdate(req);
            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            Logger.getLogger(CategorieServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    
    public List<Categorie> readC() {
         List<Categorie> ls =new ArrayList<>();
        try {
            
            PreparedStatement pt = conn.prepareStatement("select * from categorie");
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                
                Categorie c = new Categorie(rs.getInt(1), rs.getString(2));
                ls.add(c);
            }
            
            ls.stream().forEach((c) -> {
                System.out.println(c.toString());
            });
        } catch (SQLException ex) {
            Logger.getLogger(CategorieServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls ;
    }   
    public List<String> readCName() {
         List<String> ls =new ArrayList<>();
        try {
            
            PreparedStatement pt = conn.prepareStatement("select * from categorie");
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                
                Categorie c = new Categorie(rs.getInt(1), rs.getString(2));
                ls.add(c.getNom());
            }
            
            ls.stream().forEach((c) -> {
                System.out.println(c);
            });
        } catch (SQLException ex) {
            Logger.getLogger(CategorieServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls ;
    }

    
    public void updateC(int id , String nom) {
        try {
            PreparedStatement pt  =conn.prepareStatement("update categorie set nom =?,produit=? where id=?");
            pt.setString(1, nom);
            pt.setInt(2, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategorieServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void deleteC(int id) {
        try {
            PreparedStatement pt = conn.prepareStatement("delete from categorie  where id=?");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategorieServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Categorie findCByName(String nom) {
        Categorie c = null;

        try {
            String            req = "select * from categorie where nom=? ";
            PreparedStatement st  = conn.prepareStatement(req);

            st.setString(1, nom);
            

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                c = new Categorie(rs.getInt(1),
                             rs.getString(2));
 
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return c;
    }
        public Categorie findByName(String nom) {
        Categorie c = null;

        try {
            String            req = "select * from categorie where nom=? ";
            PreparedStatement st  = conn.prepareStatement(req);

            st.setString(1, nom);
            

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                c = new Categorie(rs.getInt(1),
                             rs.getString(2));
 
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return c;
    }
}
