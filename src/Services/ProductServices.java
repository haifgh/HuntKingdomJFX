/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utilities.Connexion;
import Models.Produit;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author arij
 */
public class ProductServices {
    Connection conn;
    public ProductServices() {
        conn=Connexion.getInstance().getCnx();
    
} 
     public void addP(Produit P) throws SQLException{
       
        int  insCategorie_id=P.getCategorie_id();
        String insNom=P.getNom();
        int insQte=P.getQte();
        int insPrix= P.getPrix();
        int insPrix_promo =P.getPrix_promo();
        String insDescription=P.getDescription();
        String insPhoto=P.getPhoto();
        String requete = "insert into produit (categorie_id,nom,qte,prix,prix_promo,description,photo) values ('"+insCategorie_id+"','"+insNom+"','"+insQte+"','"+insPrix +"','"+insPrix_promo +"','"+insDescription +"','"+insPhoto +"')"; 
      
        try{
            Statement st = conn.createStatement();
            st.executeUpdate(requete);
            System.out.println("Succesfully Added");
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }}
     
    public void deleteP(int id) throws SQLException{
                   
        String requete = "DELETE FROM produit WHERE id="+id;
        try{
            Statement st = conn.createStatement();
            st.executeUpdate(requete);
            System.out.println("Suppression effectué");
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Produit getP(int id) {
        Produit p = null;

        try {
            PreparedStatement pt = conn.prepareStatement("select * from produit where id=?");

            pt.setInt(1, id);

            ResultSet rs = pt.executeQuery();

            while (rs.next()) {
                p = new Produit(rs.getInt(1), rs.getInt(2),rs.getString(3), rs.getInt(4), rs.getInt(5),rs.getInt(6),rs.getString(7),rs.getString(8));
            }

            return p;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return p;
    }
    public ObservableList<Produit> getAllP() throws SQLException, IOException{
       
        ObservableList<Produit> ListProduit = FXCollections.observableArrayList();  
        String requete = "SELECT * FROM produit WHERE qte>0";    
        try{
           Statement st = conn.createStatement();
           ResultSet rs=st.executeQuery(requete);
           while(rs.next()){
                      Produit E = new Produit();
                      E.setId(rs.getInt(1));
                      E.setCategorie_id(rs.getInt(2));
                      E.setNom(rs.getString(3));
                      E.setQte(rs.getInt(4));
                      E.setPrix(rs.getInt(5));
                      E.setPrix_promo((int)rs.getDouble(7));
                      E.setDescription(rs.getString(7));
                      E.setPhoto(rs.getString(8));
                      ListProduit.add(E);                      
          }                        
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);          
        }
        return ListProduit;
    }
        
        
    public void updateP (int id,int categorie_id,String nom,int qte,int prix,int prix_promo,String description, String photo)
     {
             String requete="UPDATE produit SET categorie_id='"+categorie_id+"',nom='"+nom+"',qte='"+qte+"',prix='"+prix+"',prix_promo='"+prix_promo+"',description='"+description+"',photo='"+photo+"'";
         try{
            Statement st = conn.createStatement();
            st.executeUpdate(requete);
            System.out.println("Produit bien Modifié");
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

   
    public List<Produit> ProdByCat(int id) {
      
         List<Produit> ls =new ArrayList<Produit>();
        try {
           
            PreparedStatement pt = conn.prepareStatement("select * from Produit where categorie_id=? and qte>0 ");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                Produit E = new Produit();
                      E.setId(rs.getInt(1));
                      E.setCategorie_id(rs.getInt(2));
                      E.setNom(rs.getString(3));
                      E.setQte(rs.getInt(4));
                      E.setPrix(rs.getInt(5));
                      E.setPrix_promo((int)rs.getDouble(7));
                      E.setDescription(rs.getString(6));
                      E.setPhoto(rs.getString(8));
                ls.add(E);
            }
            
        } catch (SQLException ex) {
          
        }
        return ls ;
        
    }
    
        public Produit findPByName(String nom) {
        Produit u = null;
        try {
            String req = "select * from produit where nom=? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1,nom);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                u = new Produit(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(7),
                        rs.getString(6),
                        rs.getString(8));
 
          
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return u;
    }
    public ObservableList<Produit> getPOut() throws SQLException, IOException{
       
        ObservableList<Produit> ListProduit = FXCollections.observableArrayList();  
        String requete = "SELECT * FROM produit WHERE qte=0";    
        try{
           Statement st = conn.createStatement();
           ResultSet rs=st.executeQuery(requete);
           while(rs.next()){
                      Produit E = new Produit();
                      E.setId(rs.getInt(1));
                      E.setCategorie_id(rs.getInt(2));
                      E.setNom(rs.getString(3));
                      E.setQte(rs.getInt(4));
                      E.setPrix(rs.getInt(5));
                      E.setPrix_promo(rs.getInt(6));
                      E.setDescription(rs.getString(7));
                      E.setPhoto(rs.getString(8));
                      ListProduit.add(E);                      
          }                        
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);          
        }
        return ListProduit;
    }
}
