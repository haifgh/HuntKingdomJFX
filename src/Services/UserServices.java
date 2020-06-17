package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import Models.User;
import Util.JavaMailUtil;

import Utilities.BCrypt;
import Utilities.Connexion;
import Utilities.UserSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

/**
 *
 * @author rejeb
 */
public class UserServices {
    Connection conn = Connexion.getInstance().getCnx();
    public List<User> findUsers() {
        List<User> Al =new ArrayList<User>();
        User u = null;
        try {
            String req = "select * from fos_user";
            PreparedStatement st = conn.prepareStatement(req);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                u = new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getString(7),
                        rs.getString(8),
                        (Timestamp) rs.getObject(9),
                        rs.getString(10),
                        (Timestamp) rs.getObject(11),
                        rs.getString(12),
                        rs.getString(18));
               Al.add(u);
          
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return Al;
    }
    public boolean Authentification(User u) {
        boolean status = false;

        try {
            String            req = "select * from fos_user where username=? ";
            PreparedStatement st  = conn.prepareStatement(req);

            st.setString(1, u.getUsername());

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                if (BCrypt.checkpw(u.getPassword(), rs.getString("password")) == true) {
                    status = true;
                    u      = this.findById(rs.getInt("id"));
                    UserSession.getInstance(u);
                    System.out.println("is admin: " + u.isAdmin());
                } else {
                    status = false;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return status;
    }
    public User findByUsername(String username) {
        User u = null;
        try {
            String req = "select * from fos_user where Username=? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1,username);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                u = new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getString(7),
                        rs.getString(8),
                        (Timestamp) rs.getObject(9),
                        rs.getString(10),
                        (Timestamp) rs.getObject(11),
                        rs.getString(12),
                         rs.getString(18));
            
          
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return u;
    }
    public void create(User u) {
        try {
            String req =
                "INSERT INTO `fos_user`(`username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(req);

            // String token = UUID.randomUUID().toString();
            st.setString(1, u.getUsername());
            st.setString(2, u.getUsernameCanonical());
            st.setString(3, u.getEmail());
            st.setString(4, u.getEmailCanonical());
            st.setInt(5, 1);
            st.setString(6, u.getSalt());
            st.setString(7, BCrypt.hashpw(u.getPassword(), BCrypt.gensalt()));
            st.setObject(8, u.getLastLogin());
            st.setString(9, null);
            st.setObject(10, null);
            st.setString(11, "a:0:{}");
            st.executeUpdate();
            System.out.println("user added");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public User findById(Integer id) {
        User u = null;

        try {
            String            req = "select * from fos_user where id=? ";
            PreparedStatement st  = conn.prepareStatement(req);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                u = new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getString(7),
                        rs.getString(8),
                        (Timestamp) rs.getObject(9),
                        rs.getString(10),
                        (Timestamp) rs.getObject(11),
                        rs.getString(12),
                         rs.getString(18));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(u);
        return u;
    }

    public void logout() {
        UserSession.setInstance(null);
    }
     public void BlockUserR(int id , String recipient) throws MessagingException{
         try {
           PreparedStatement   pt = conn.prepareStatement("update fos_user set enabled=? where id=?");
             
           pt.setInt(1,0);
           pt.setInt(2, id);
             pt.executeUpdate();
         JavaMailUtil js = new JavaMailUtil();
        js.sendMail(recipient);
         } catch (SQLException ex) {
             Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
         }
            
  }
  public void UnBlockUserR(int id , String recipient) throws MessagingException{
         try {
           PreparedStatement   pt = conn.prepareStatement("update fos_user set enabled=1 where id=?");
             
          // pt.setInt(1,1);
           pt.setInt(1, id);
           pt.executeUpdate();
           JavaMailUtil js = new JavaMailUtil();
           js.unsendMail(recipient);
         } catch (SQLException ex) {
             Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
         }
            
  }
     public void updateImage(String username,String email,int tel,String adresse,String image, int id) throws MessagingException{
         try {
           PreparedStatement   pt = conn.prepareStatement("update fos_user set username=?,email=?,tel=?,adresse=?, photo=? where id=?");
             
           pt.setString(1,username);
           pt.setString(2,email);
           pt.setInt(3, tel);
           pt.setString(4,adresse);
           pt.setString(5,image);
           pt.setInt(6,id);
           pt.executeUpdate();
          
         } catch (SQLException ex) {
            
         }
            
  }
}


//~ Formatted by Jindent --- http://www.jindent.com
