/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Commentaire;
import Models.Guide;
import Models.User;
import Utilities.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author haifa
 */
public class ServiceCommentaire {
    Connection con;

    public ServiceCommentaire(){
        con=Connexion.getInstance().getCnx();
    }
    
    
    public void ajoutercommentaire(Commentaire c){
        try {
        java.util.Date date1 = new java.util.Date();
        String date= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date1);
        c.setDate(date);
            Statement st = con.createStatement();
            String req="insert into commentaire values ("+c.getId()+", '"+c.getGuide_id()+"','"+c.getUser_id()+"','"+c.getContenu()+"','"+c.getDate()+"')";
            st.executeUpdate(req);
            System.out.println("commentaire inseré");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    
    
        public void modifiercommentaire(Commentaire c){
        try {
            PreparedStatement pt  =con.prepareStatement("update commentaire set guide_id=?,contenu=? where id=?");
            pt.setInt(3, c.getId());
            pt.setString(2, c.getContenu());
            pt.setInt(1, c.getGuide_id());
            pt.executeUpdate();
            
            int rowsUpdated = pt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification de commentaire" + c.getId() + " a été éffectué avec succée ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void supprimercommentaire(Commentaire c){
        
        try {
            PreparedStatement pt = con.prepareStatement("delete from commentaire where id=?");
            pt.setInt(1,c.getId());
            pt.executeUpdate();
             System.out.println("commentaire supprimer");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Commentaire> affichercommentaire(){
       
             Statement  pt = null ;
            List<Commentaire> ls =new ArrayList<Commentaire>();
            ls.clear();
            String query = "SELECT * FROM commentaire  "; //pagination
        try {

      pt = con.createStatement();
    ResultSet     rs = pt.executeQuery(query);
        
            while(rs.next()){
                
                ls.add(new Commentaire(rs.getInt("guide_id")
                        ,rs.getInt("user_id")
                        ,rs.getString("contenu")
                        ,rs.getString("date")));

            }
            ls.stream().forEach((p) -> {
                System.out.println(p.toString());
            });
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
           
            if (pt != null) {
                try {
                    pt.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
return ls;
        
        
        
        
        
    }
    /*public Guide findGuideById(int id) {
        
       try {
             Guide gu=new Guide();
             int c=0;
             String req="select * from guide where id="+id;
             Statement st=con.createStatement();
             ResultSet rs=st.executeQuery(req);
             while(rs.next())
             {
                 gu.setId(rs.getInt(1));
                gu.setTitre(rs.getString(2));
                gu.setDate_creation(rs.getString(3));
                gu.setCategorie(rs.getString(4));
                gu.setDescription(rs.getString(5));
                gu.setLien(rs.getString(6));
                gu.setPhoto(rs.getString(7));
                gu.setNbLikes(rs.getInt(8));
                 c++;
                         }
             if(c==0)
             {
                 return null;
             }else {
                 return gu;
             }
         } catch (SQLException ex) {
             Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
             return null;

         }
       } */
    
    

    public User findUserById(int id) {
          try {
             User u=new User();
             int c=0;
             String req="select * from fos_user where id="+id;
             Statement st=con.createStatement();
             ResultSet rs=st.executeQuery(req);
             while(rs.next())
             {
                 u.setId(rs.getInt(1));
                 u.setUsername(rs.getString(2));
                 c++;
                         }
             return u;
         } catch (SQLException ex) {
             Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
             return null;

         }
       
    }
public ObservableList<Commentaire> getAllCommentByGuide(Guide g) throws SQLDataException {
   try {

             List<Commentaire> list=new ArrayList<Commentaire>();
             int c=0;
             String req="select * from commentaire where guide_id="+g.getId();
             Statement st=con.createStatement();
             ResultSet rs=st.executeQuery(req);
             
             while(rs.next())
             {
                 Commentaire cm=new Commentaire();
                 cm.setId(rs.getInt(1));
                 cm.setGuide_id(rs.getInt(2));
                 cm.setUser_id((rs.getInt(3)));
                 cm.setContenu(rs.getString(4));
                 
                 list.add(cm);
                 c++;
                 
             }
             if (c == 0)
             {
                 return null;
             }else {
                              ObservableList lc = FXCollections.observableArrayList(list);

                 return lc;
             }
         } catch (SQLException ex) {
             Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
             return null;
        
        




}}

 public void removeComment(int id) 
       {
                   
        String requete = "DELETE FROM commentaire WHERE id="+id;
        try{
            Statement st = con.createStatement();
            st.executeUpdate(requete);
            System.out.println("Suppression effectué");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
