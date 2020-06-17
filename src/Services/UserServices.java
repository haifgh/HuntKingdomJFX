package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import Models.User;

import Utilities.BCrypt;
import Utilities.Connexion;
import Utilities.UserSession;

/**
 *
 * @author rejeb
 */
public class UserServices {
    Connection conn = Connexion.getInstance().getCnx();

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
                             rs.getString(12));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return u;
    }

    public void logout() {
        UserSession.setInstance(null);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
