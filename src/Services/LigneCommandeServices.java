
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

import Models.LigneCommande;

import Utilities.Connexion;

/**
 *
 * @author rejeb
 */
public class LigneCommandeServices {
    Connection conn = Connexion.getInstance().getCnx();

    public LigneCommande FindOne(int id) {
        LigneCommande lc = null;

        try {
            String    select    = "SELECT * FROM ligne_commande WHERE  id = '" + id + "' ";
            Statement statement = conn.createStatement();
            ResultSet result    = statement.executeQuery(select);

            if (result.next()) {
                lc = new LigneCommande();
                lc.setId(id);
                lc.setIdC(result.getInt("commande_id"));
                lc.setIdProduit(result.getInt("produit_id"));
                lc.setPrice(result.getDouble("prix"));
                lc.setQuantity(result.getInt("quantite"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return lc;
    }

    public void create(LigneCommande e) {
        try {
            String            req =
                "INSERT INTO ligne_commande (commande_id,produit_id,prix,quantite) VALUES (?,?,?,?)";
            PreparedStatement st  = conn.prepareStatement(req);

            st.setInt(1, e.getIdC());
            st.setInt(2, e.getIdProduit());
            st.setDouble(3, e.getPrice());
            st.setInt(4, e.getQuantity());
            st.execute();
            System.out.println("done");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(int id) {
        try {
            String            req = "DELETE FROM ligne_commande WHERE ligne_commande.id = ? ";
            PreparedStatement st  = conn.prepareStatement(req);

            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("deleted ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<LigneCommande> findAll(int id) {
        ObservableList<LigneCommande> lcs = FXCollections.observableArrayList();

        try {
            String            req = "select * from ligne_commande where ligne_commande.commande_id=?";
            PreparedStatement ps  = conn.prepareStatement(req);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LigneCommande lc = new LigneCommande(rs.getInt("id"),
                                                     rs.getInt("quantite"),
                                                     rs.getDouble("prix"),
                                                     rs.getInt("produit_id"),
                                                     rs.getInt("commande_id"))
                ;

                lcs.add(lc);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return lcs;
    }

    public void update(LigneCommande e) {
        try {
            String            req =
                "UPDATE ligne_commande SET commande_id = ?, produit_id= ?, prix=?, quantite=? WHERE `id` = ?";
            PreparedStatement st  = conn.prepareStatement(req);

            st.setInt(1, e.getIdC());
            st.setInt(2, e.getIdProduit());
            st.setDouble(3, e.getPrice());
            st.setInt(4, e.getQuantity());
            st.setInt(5, e.getId());
            st.executeUpdate();
            System.out.println("updated ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
