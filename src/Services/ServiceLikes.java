/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Likes;
import Utilities.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author haifa
 */
public class ServiceLikes {
    
     Connection con;
public ServiceLikes(){
      con=Connexion.getInstance().getCnx();
    }

    public void ajouterJaime(Likes j) throws SQLException {
          Statement st = con.createStatement();
        String req1 = "INSERT INTO `likes` (`id` , `id_user`, `id_guide`) "
                + "VALUES ('" + j.getId() + "', '" + j.getId_user() + "', '" + j.getId_guide() + "');";

        st.executeUpdate(req1);
        System.out.println("j'aime ajouter");

    }
    
    
    
    public void supprimerJaime(Likes l){
        
        try {
            PreparedStatement pt = con.prepareStatement("delete from likes where id=?");
            pt.setInt(1,l.getId());
            pt.executeUpdate();
             System.out.println("Like supprimer");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLikes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int FindValeurJaimeByIdUserAndIdSujet(int id_user, int id_guide) throws SQLException {
        Statement st = con.createStatement();
        String req = "SELECT * from Likes WHERE id_user='" + id_user + "'AND id_guide='" + id_guide + "'";
        ResultSet res = st.executeQuery(req);
        Likes jaime = null;
        int valeurJaime = 0;
        while (res.next()) {

            valeurJaime = res.getInt(3);
            System.out.println(valeurJaime);

        }
        return valeurJaime;
    }

   /* public void incrementerjaime(int id_user, int id_guide) {
        try {
             int valeurJaime = 0;
            PreparedStatement ste = con.prepareStatement("update Likes set valeur_jaime=valeur_jaime+1  WHERE id_user='" + id_user + "'AND id_guide='" + id_guide + "'");
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLikes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void decrementerjaime(int id_user, int id_guide) {
        try {
            PreparedStatement ste = con.prepareStatement("update Likes set valeur_jaime=valeur_jaime-1  WHERE id_user='" + id_user + "'AND id_guide='" + id_guide + "'");

            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLikes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
  /*  public double count(int id) {
         double count = 0;
        try {
            String req = "SELECT AVG(valeur_jaime) FROM `rate` WHERE `id_user` = ?";
                PreparedStatement st = con.prepareStatement(req);
                st.setInt(1, id);
                ResultSet rs = st.executeQuery();
                
            while (rs.next()) {
                count = rs.getDouble("AVG(valeur_jaime)");
                System.out.println(count);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGuide.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return count;
    }*/
    public int nbLikes(int id_guide)
    {
        int nb = 0;
        try {
           // Statement st = con.createStatement();
            //String req="SELECT AVG(note)FROM likes WHERE id_guide=?";
           PreparedStatement st = con.prepareStatement("select  count(*) from rate where id=?");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
            nb=rs.getInt(1);}
               } catch (SQLException ex) {
            Logger.getLogger(ServiceLikes.class.getName()).log(Level.SEVERE, null, ex);
        }
       return nb;
    }
    
    public boolean verifLike(int idG,int idU) throws SQLException{
        String requete = "select * from likes WHERE id_user="+idG+" AND id_guide="+idU;
        int idLike=0;
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                idLike=rs.getInt(1);
              
            }
        }catch (SQLException ex){
        Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(idLike == 0){
            return false;}
        else {return true;  } 
        }
    public void likerArticle(int idG,int idU) throws SQLException{
        //if(!verifLike(idG,idU)){
        String requete = "INSERT into likes(id_guide,id_user)values ('"+idG+"','"+idU+"')";
        try{
        Statement st = con.createStatement();
            st.executeUpdate(requete);
            System.out.println("like ajouté");
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        //incrementNbLikes(idG);
       // }else{
            System.out.println("Vous likez deja l'article");
            //decrementerNblikes(idG);
         }
    
    
     public void DislikerArticle(int idG) throws SQLException{
        
        String requete = "DELETE from likes where id_guide="+idG;
        try{
        Statement st = con.createStatement();
            st.executeUpdate(requete);
            System.out.println("like deleted");
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
     }
    
    public void incrementNbLikes(int IdG){
         String requete="UPDATE guide SET nbLikes=nbLikes+1  WHERE id="+IdG;
         try{
            Statement st = con.createStatement();
            st.executeUpdate(requete);
            System.out.println("like added");
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
    public void decrementerNblikes( int id_guide) {
        try {
            PreparedStatement ste = con.prepareStatement("update guide set nbLikes=nbLikes-1  WHERE  id='" + id_guide + "'");

            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
     public void DislikerArticle( Likes l) throws SQLException{
        //if(!verifLike(idG,idU)){
        String requete = "DELETE from likes where id=?";
        try{
        Statement st = con.createStatement();
            st.executeUpdate(requete);
            System.out.println("like supprimer");
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        //incrementNbLikes(idG);
       // }else{
            System.out.println("Vous likez deja l'article");
          //  decrementerNblikes(idG);
         }
    
}
