/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Util.Connexion;
import Util.UserSession;
import entities.Jaime;
import interfaces.IJaimeServices;
import javafx.scene.control.Button;

/**
 *
 * @author ghofrane
 */
public class ServiceJaime  implements IJaimeServices {
      Connection con;

    public ServiceJaime() {
        con=Connexion.getInstance().getCnx();
    }
     public void ajouterjaime(Jaime j){
        
        try {
            Statement st = con.createStatement();
            String req="insert into jaime values ("+j.getId()+",'"+UserSession.getUser().getId()+"' ,'"+j.getPost()+"')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceJaime.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
     public int nbrejaime(int id){
         List<Jaime> ls =new ArrayList<Jaime>(); 
         try {
            PreparedStatement st = con.prepareStatement("select *  from jaime where post=?");
            st.setInt (1,id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Jaime j = new Jaime(rs.getInt(2), rs.getInt(3));
                ls.add(j);
                
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(ServiceJaime.class.getName()).log(Level.SEVERE, null, ex);
        }
       return ls.size();  
     }
     
      public List<Jaime> reloadjaime(int user , int post){
        List<Jaime> ls =new ArrayList<Jaime>();
        try {
            
            PreparedStatement pt = con.prepareStatement("select * from jaime where user = ? AND  post = ?");
            pt.setInt(1,user);
            pt.setInt(2,post);
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                
                Jaime j = new Jaime(rs.getInt(1),rs.getInt(3), rs.getInt(2));
                ls.add(j);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceJaime.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls ;
    }
       public void supprimerjaime(int id){
        
        try {
           
            PreparedStatement pt = con.prepareStatement("delete from jaime where id=?");
            pt.setInt(1,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceJaime.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  

   
}
