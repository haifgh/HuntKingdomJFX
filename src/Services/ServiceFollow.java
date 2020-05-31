/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Array;
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
import entities.Follow;
import entities.Post;
import interfaces.IFollowServices;
        /**
 *
 * @author ghofrane
 */
public class ServiceFollow implements IFollowServices {
      Connection con;

    public ServiceFollow() {
        con=Connexion.getInstance().getCnx();
    }
     public void follow(Follow f){
        try {
            Statement st = con.createStatement();
            String req="insert into follow values ("+f.getId()+", '"+UserSession.getUser().getId()+"','"+f.getFollowed()+"')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFollow.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
     
       public void unfollow(int id){
        
        try {
            PreparedStatement pt = con.prepareStatement("delete from follow where id=? ");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFollow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Follow> reloadfollow(int user , int followed ){
         List<Follow> ls =new ArrayList<Follow>();
        try {
           
            PreparedStatement pt = con.prepareStatement("select * from follow where follower=? AND followed=? ");
            pt.setInt(1, user);
            pt.setInt(2, followed);
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                
                Follow f = new Follow(rs.getInt(1),rs.getInt(2), rs.getInt(3));
                ls.add(f);
            }
            
            for (Follow f : ls){
                System.out.println(f.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFollow.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls ;
        
    }
     public List<Post> afficherpostfollow(int follower){
         List<Post> ls =new ArrayList<Post>(); 
        try {
            
            PreparedStatement pt = con.prepareStatement("select * from post where user_id IN (select followed from follow where  follower =? )");
            pt.setInt(1, follower);
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                
               Post p = new Post(rs.getInt(1),rs.getString(4), rs.getString(3), rs.getInt(2));
                ls.add(p);
            }
            
             for (Post p : ls){
                System.out.println(p.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFollow.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
        
    }
        public List<Follow> following (int user ){
         List<Follow> ls =new ArrayList<Follow>();
        try {
           
            PreparedStatement pt = con.prepareStatement("select * from follow where follower=?");
            pt.setInt(1, user);
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                
                Follow f = new Follow(rs.getInt(1),rs.getInt(2), rs.getInt(3));
                ls.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFollow.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls ;
        
    }
        public List<Follow> follower (int user ){
         List<Follow> ls =new ArrayList<Follow>();
        try {
           
            PreparedStatement pt = con.prepareStatement("select * from follow where followed =?");
            pt.setInt(1, user);
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                
                Follow f = new Follow(rs.getInt(1),rs.getInt(2), rs.getInt(3));
                ls.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFollow.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls ;
        
    }
}
