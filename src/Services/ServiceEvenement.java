/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Models.Evenement;
import Utilities.Connexion;
import Utilities.UserSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Yosrio
 */
public class ServiceEvenement  {
 
   

     private Connection con;
    private Statement ste;

    public ServiceEvenement() {
        con = Connexion.getInstance().getCnx();
    }
    
    public void ajouter(Evenement a) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `pidev`.`evenement` (`id`,`user_id`, `nom`, `description`,`date_debut`, `date_fin`, `validite`,`nbre_places`,`photo`) VALUES ( '" + a.getId()+  "','" + UserSession.getInstance().getUser().getId()+  " ','" + a.getNom()+ "', '" + a.getDescription()+  "','" + a.getDate_debut()+  "','" + a.getDate_fin()+"','" + a.getValidite()+  "','" + a.getNbre_places()+  "','" + a.getPhoto()+  "');";
        ste.executeUpdate(requeteInsert);
        System.out.println("evenement ajouté");
    }
     

    
    public void delete(Evenement t) throws SQLException {
      String querry = "DELETE FROM evenement WHERE id =?";
        PreparedStatement d = con.prepareStatement(querry);
        d.setInt(1, t.getId());
        d.executeUpdate();
        System.out.println("evenement supprimé");
    }
   
       public void update(int id,String nom,String description,Date date_debut,Date date_fin,int nbre_places,String photo) throws SQLException {
        PreparedStatement pla = con.prepareStatement("update evenement set   nom=? , description=?, date_debut= ?, date_fin= ? ,nbre_places=?,photo=? where id=?");
        //pla.setInt(1,user_id ); 
        pla.setString(1, nom);
        pla.setString(2, description);
        pla.setDate(3, date_debut);
        pla.setDate(4, date_fin);
        //pla.setDate(5, date_fin);
        //pla.setString(6, validite);
        pla.setInt(5, nbre_places);
        pla.setString(6, photo);
        pla.setInt(7,id);
        pla.executeUpdate();
        System.out.println("evenement modifié");

    }
   

  
    public List<Evenement> readAll() throws SQLException {
        List<Evenement> arr=new ArrayList<>();
    ste=con.createStatement();
    
    ResultSet rs=ste.executeQuery("select * from evenement");
     while (rs.next()) {                
               int id=rs.getInt("id");
               int user_id=rs.getInt("user_id");
               String nom=rs.getString("nom");
               String description=rs.getString("description");
               Date date_debut = rs.getDate("date_debut");
               Date date_fin = rs.getDate("date_fin");
               String validite=rs.getString("validite");
               int nbre_places=rs.getInt("nbre_places");
               String photo=rs.getString("photo");
               Evenement e=new Evenement(id, user_id, nom, description,date_debut,date_fin,validite
               ,nbre_places,photo);
               arr.add(e);
               }
         return arr;
         }

     public List<Evenement> findAllwithInscriptionsCount() {
        List<Evenement> result = new ArrayList<>();
        try {
            String query = "select e.*,"
                    + " (select count(*) from liste_participants ie where ie.evenement_id = e.id) as nb_inscriptions "
                    + "from evenement e";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Evenement e = new Evenement(rs.getInt("id")
                        , rs.getString("nom")
                        ,rs.getString("description"), 
                        rs.getDate("date_debut"),
                        rs.getDate("date_fin"),  
                        rs.getInt("nbre_places"),rs.getString("photo"));
                e.setNbrInscriptions(rs.getInt("nb_inscriptions"));
               result.add(e);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
     
      public List<Evenement> readdAll() throws SQLException {
        List<Evenement> arr=new ArrayList<>();
    ste=con.createStatement();
    
    ResultSet rs=ste.executeQuery("select nom,description,nbre_places from evenement");
     while (rs.next()) {                
               
               String nom=rs.getString("nom");
               String description=rs.getString("description");
               int nbre_places=rs.getInt("nbre_places");
               Evenement e=new Evenement( nom, description,nbre_places);
               arr.add(e);
               }
         return arr;
         }
}
