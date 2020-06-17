
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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import Models.Produit;

import Utilities.Connexion;

/**
 *
 * @author rejeb
 */
public class ProductServices {
    Connection con = Connexion.getInstance().getCnx();

    public ObservableList<Produit> findAll() {
        ObservableList<Produit> ls = FXCollections.observableArrayList();

        try {
            PreparedStatement pt = con.prepareStatement("select * from produit");
            ResultSet         rs = pt.executeQuery();

            while (rs.next()) {
                Produit p = new Produit(rs.getInt(1), rs.getString(3), rs.getInt(4), rs.getInt(5));

                ls.add(p);
            }

            return ls;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

            return ls;
        }
    }

    public Produit getP(int id) {
        Produit p = null;

        try {
            PreparedStatement pt = con.prepareStatement("select * from produit where id=?");

            pt.setInt(1, id);

            ResultSet rs = pt.executeQuery();

            while (rs.next()) {
                p = new Produit(rs.getInt(1), rs.getString(3), rs.getInt(4), rs.getInt(5));
            }

            return p;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return p;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
