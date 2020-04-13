
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import Models.Commande;

import Utilities.Connexion;
import Utilities.UserSession;

/**
 *
 * @author rejeb
 */
public class CommandeServices {
    Connection conn = Connexion.getInstance().getCnx();

    public Commande FindOne(int id) {
        Commande c = null;

        try {
            String    select    = "SELECT * FROM commande WHERE  id = '" + id + "' ";
            Statement statement = conn.createStatement();
            ResultSet result    = statement.executeQuery(select);

            if (result.next()) {
                c = new Commande();
                c.setId(id);
                c.setUserId(result.getInt("user_id"));
                c.setAddresse(result.getString("adresse"));
                c.setChargeId(result.getString("charge_id"));
                c.setDate(result.getTimestamp("date"));
                c.setDateLivraison(result.getTimestamp("date_livraison"));
                c.setPrixTotal(result.getDouble("prix_total"));
                c.setStatus(result.getString("status"));
                c.setTel(result.getString("tel"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return c;
    }

    public int create(Commande e) {
        try {
            String req =
                "INSERT INTO commande (user_id,date,date_livraison,status,adresse,tel,prix_total,charge_id) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, UserSession.getInstance().getUser().getId());
            st.setTimestamp(2, e.getDate());
            st.setTimestamp(3, e.getDateLivraison());
            st.setString(4, e.getStatus());
            st.setString(5, e.getAddresse());
            st.setString(6, e.getTel());
            st.setDouble(7, e.getPrixTotal());
            st.setString(8, e.getChargeId());
            st.execute();

            ResultSet rs = st.getGeneratedKeys();

            if (rs.next()) {
                System.out.println(rs.getLong(1));

                return (int) rs.getLong(1);
            }

            return -1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

            return -1;
        }
    }

    public void delete(int id) {
        try {
            String            req = "DELETE FROM commande WHERE commande.id = ? ";
            PreparedStatement st  = conn.prepareStatement(req);

            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("deleted ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<Commande> findAll() {
        ObservableList<Commande> cs = FXCollections.observableArrayList();

        try {
            String            req = "select * from commande";
            PreparedStatement ps  = conn.prepareStatement(req);
            ResultSet         rs  = ps.executeQuery();

            while (rs.next()) {
                Commande c = new Commande(rs.getInt("id"),
                                          rs.getInt("user_id"),
                                          rs.getDouble("prix_total"),
                                          rs.getTimestamp("date"),
                                          rs.getTimestamp("date_livraison"),
                                          rs.getString("status"),
                                          rs.getString("charge_id"),
                                          rs.getString("adresse"),
                                          rs.getString("tel"));

                cs.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return cs;
    }

    public void update(Commande e) {
        try {
            String req =
                "UPDATE commande SET user_id = ?, date= ?, date_livraison=?, status=?,adresse= ?, tel=?, prix_total=?, charge_id=? WHERE `id` = ?";
            PreparedStatement st = conn.prepareStatement(req);

            st.setInt(1, e.getUserId());
            st.setTimestamp(2, e.getDate());
            st.setTimestamp(3, e.getDateLivraison());
            st.setString(4, e.getStatus());
            st.setString(5, e.getAddresse());
            st.setString(6, e.getTel());
            st.setDouble(7, e.getPrixTotal());
            st.setString(8, e.getChargeId());
            st.setInt(9, e.getId());
            st.executeUpdate();
            System.out.println("updated ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
