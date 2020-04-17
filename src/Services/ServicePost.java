/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Post;
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
import interfaces.IPostServices;


/**
 *
 * @author ghofrane
 */
public class ServicePost implements IPostServices {
     Connection con;

    public ServicePost() {
        con=Connexion.getInstance().getCnx();
    }
     public void ajouterPost(Post p){
        try {
            java.util.Date date1 = new java.util.Date();
        String dateCreation= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date1);
        p.setDateCreation(dateCreation);
            Statement st = con.createStatement();
            String req="insert into post values ("+p.getId()+", '"+UserSession.getUser().getId()+"','"+p.getDateCreation()+"', '"+p.getContenu()+"')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServicePost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
     public void modifierpost(int id , String contenu){
        try {
            PreparedStatement pt  =con.prepareStatement("update post set contenu =? where id=?");
            pt.setString(1, contenu);
            pt.setInt(2, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePost.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
       public void supprimerpost(int id){
        
        try {
            PreparedStatement pt = con.prepareStatement("delete from post where id=?");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @Override
    public List<Post> afficherpost(int user_id) {
         List<Post> ls =new ArrayList<Post>();
        try {
           
            PreparedStatement pt = con.prepareStatement("select * from post where user_id=?");
            pt.setInt(1, user_id);
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                
                Post p = new Post(rs.getInt(1),rs.getString(4), rs.getString(3), rs.getInt(2));
                ls.add(p);
            }
            
            for (Post p : ls){
                System.out.println(p.toString());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePost.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls ;
        
    }

    
}

   

