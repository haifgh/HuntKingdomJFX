/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Util.Connexion;
import Util.UserSession;
import interfaces.IReclamationServices;
/**
 *
 * @author ghofrane
 */
public class ServiceReclamation implements IReclamationServices {
     Connection con;

    public ServiceReclamation() {
        con=Connexion.getInstance().getCnx();
    }
     public void ajouterreclamation(Reclamation r){
        try {
            java.util.Date date1 = new java.util.Date();
        String date= new SimpleDateFormat("yyyy-MM-dd").format(date1);
        r.setDate(date);
            Statement st = con.createStatement();
           String req="insert into reclamation values ("+r.getId()+",'"+UserSession.getUser().getId()+"','"+r.getReclamer()+"','"+r.getObject()+"','"+r.getContenu()+"','"+r.getDate()+"')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
     public void modifierreclamation(int id , String contenu , String object ){
        try {
            PreparedStatement pt  =con.prepareStatement("update reclamation set contenu =?,objet =? where id=?");
              pt.setString(1,contenu);
              pt.setString(2,object);
              pt.setInt(3,id );
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
       public void supprimerreclamation(int id){
        
        try {
            PreparedStatement pt = con.prepareStatement("delete from reclamation where id=?");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Reclamation> afficherReclamation(int user ){
         List<Reclamation> ls =new ArrayList<Reclamation>();
        try {
           
            PreparedStatement pt = con.prepareStatement("select * from reclamation where user_id=?");
            pt.setInt(1, user);
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
              // Reclamation r = new Reclamation (rs.getString(5), rs.getString(4), rs.getString(3), rs.getInt(1), rs.getInt(6), rs.getInt(2));
               Reclamation r = new Reclamation (rs.getString(6), rs.getString(5), rs.getString(4), rs.getInt(1), rs.getInt(3), rs.getInt(2));
                
                ls.add(r);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }
    public List<Reclamation> afficherReclamationA(){
         List<Reclamation> ls =new ArrayList<Reclamation>();
        try {
           
            PreparedStatement pt = con.prepareStatement("select * from reclamation");
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
             //  Reclamation r = new Reclamation (rs.getString(5), rs.getString(4), rs.getString(3), rs.getInt(1), rs.getInt(6), rs.getInt(2));
                //Reclamation r = new Reclamation (rs.getInt(6), rs.getInt(2));
                  Reclamation r = new Reclamation (rs.getString(6), rs.getString(5), rs.getString(4), rs.getInt(1), rs.getInt(3), rs.getInt(2));
                ls.add(r);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }
    public void showReclamation(int id ){
        try {
           
            PreparedStatement pt = con.prepareStatement("select * from reclamation where id=?");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
               Reclamation r = new Reclamation (rs.getString(5), rs.getString(4), rs.getString(3), rs.getInt(1), rs.getInt(6), rs.getInt(2));
                //Reclamation r = new Reclamation (rs.getInt(6), rs.getInt(2));
                
            }
            
            
            }
         catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
}
