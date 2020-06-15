/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Guide;
import Models.rate;
import Utilities.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author haifa
 */
public class ServiceRate {
       Connection con;
  public  ServiceRate(){
      con=Connexion.getInstance().getCnx();
}
   
       public void ajouterRate(rate r)  throws SQLException  {
          
              // Statement st = con.createStatement();
       // String req ="insert into rate ("+r.getId()+"','"+r.getId_user()"','"+r.getId_guide()+"','"+r.getNote()+"')";
      // String req="insert into rate VALUES ("+r.getId_user()+"','"+r.getId_guide()+"','"+r.getNote()+"')";   
      // String req="INSERT INTO `rate`(`id_user`, `id_guide`, `note`) VALUES (?,?,?)";
        //  st.executeUpdate(req);
          //  System.out.println("rate inseré");
           Statement st = con.createStatement();
        String req1 = "INSERT INTO `rate` (`id` , `id_user`, `id_guide`,`note`) "
                + "VALUES ('" + r.getId() + "', '" + r.getId_user() + "', '" + r.getId_guide() + "','"+r.getNote()+"');";

        st.executeUpdate(req1);
        System.out.println("vous avez  ajouter un vote");
             
        
       }
     
    public double count(int id_guide) {
         double count = 0;
        try {
            String req = "SELECT AVG(note) FROM rate";
                PreparedStatement st = con.prepareStatement(req);
                st.setInt(1,id_guide);
                ResultSet rs = st.executeQuery();
                
            while (rs.next()) {
                count = rs.getDouble("AVG(note)");
                System.out.println(count);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return count;
    }
      public ArrayList<rate> getByIdUser(int id) {
        ArrayList<rate> listRate = new ArrayList<>();
        try {
            String req = "SELECT * FROM rate WHERE id_guide=?";
                PreparedStatement ste = con.prepareStatement(req);
                ste.setInt(1, id);
                ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                listRate.add(new rate(rs.getInt(1),
                            rs.getInt("id_user"),
                            rs.getInt("id_guide"),
                             rs.getInt("note")
                            
                            
                ));
            }
            
            System.out.println(listRate.toString());
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listRate;
    }
   public void update(rate r) {
        try {
            String req = "UPDATE `rate` SET `id_guide`=?,`note`=? WHERE `id_user` = ?";
            PreparedStatement st = con.prepareStatement(req);
           
             st.setInt(1, r.getId_guide());
          
            st.setInt(2, r.getNote());
          
            st.setInt(3, r.getId());
            
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGuide.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  public void deleteRate(int id) {
        try {
            String req= "DELETE FROM `rate` WHERE `id` = ? ";
            PreparedStatement st = con.prepareStatement(req);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
  public float getRateGuide(Guide e) {

        float moy = 0.0f;

        try {
            PreparedStatement st = con.prepareStatement("SELECT AVG(note) FROM `rate` where id = '" + e.getId() + "'");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                moy = rs.getFloat(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceRate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return moy;

    }
  
  public void addRarting(Guide e) {
        try {
//            String req=("INSERT INTO `annonce`(`id`, `id_user`, `rating`, `nbrrating`, `nbruser`) VALUES (?,?,?,?,?)");
            String req = ("update `rate` SET `note` =? WHERE id_guide = '" + e.getId() + "' ");

            PreparedStatement st = con.prepareStatement(req);
            st.setDouble(1, e.getNote());

//                      
            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceRate.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
