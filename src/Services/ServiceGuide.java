/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Models.Guide;
import Models.Likes;
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

/**
 *
 * @author haifa
 */
public class ServiceGuide {
    
    String query;
    Connection con;
public ServiceGuide(){
      con=Connexion.getInstance().getCnx();
}
    public void ajouterguide(Guide g)throws SQLException{
        try {
        java.util.Date date1 = new java.util.Date();
        String date_creation= new SimpleDateFormat("yyyy-MM-dd").format(date1);
        g.setDate_creation(date_creation);
            Statement st = con.createStatement();
            String req="insert into guide values ("+g.getId()+", '"+g.getTitre()+"','"+g.getDate_creation()+"','"+g.getCategorie()+"','"+g.getDescription()+"','"+g.getLien()+"','"+g.getNote()+"','"+g.getPhoto()+"')";
            st.executeUpdate(req);
            System.out.println("guide inseré");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGuide.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
 /*public void ajouterguide(String titre, String categorie, String description, String lien,Guide g ,File selectedFile){
        try {
        java.util.Date date1 = new java.util.Date();
        String date_creation= new SimpleDateFormat("yyyy-MM-dd").format(date1);
        g.setDate_creation(date_creation);
        Statement st = con.createStatement();
            String req="insert into guide values ("+g.getId()+", '"+g.getTitre()+"','"+g.getDate_creation()+"','"+g.getCategorie()+"','"+g.getDescription()+"','"+g.getLien()+"','"+g.getPhoto()+"','"+g.getNote()+"')";
            st.executeUpdate(req);
            System.out.println("guide inseré");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGuide.class.getName()).log(Level.SEVERE, null, ex);
        }
    
 }*/
    
    
        public void modifierguide(Guide g){
        try {
            PreparedStatement pt  =con.prepareStatement("update guide set titre=?,categorie=?,description=?,lien=?,photo=? where id=?");
           
            pt.setString(1, g.getTitre());
            pt.setString(2, g.getCategorie());
            pt.setString(3,g.getDescription());
            pt.setString(5, g.getLien());
            pt.setString(4, g.getPhoto());
             pt.setInt(6, g.getId());
            pt.executeUpdate();
            
            int rowsUpdated = pt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification de guide" + g.getId() + " a été éffectué avec succée ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGuide.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void supprimerguide(Guide g){
        
        try {
            PreparedStatement pt = con.prepareStatement("delete from guide where id=?");
            pt.setInt(1,g.getId());
            pt.executeUpdate();
             System.out.println("guide supprimer");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGuide.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public List<Guide> afficherguide() {
         Statement  st = null ;
        List<Guide> lsguide = new ArrayList<Guide>();
        lsguide.clear();
        query = "SELECT * FROM Guide  ";//pagination
        try {

      st = con.createStatement();
    ResultSet     RS = st.executeQuery(query);
            while (RS.next()) {

                lsguide.add(new Guide(RS.getInt("id")
                        ,RS.getString("titre")
                        ,RS.getString("date_creation")
                        ,RS.getString("categorie")
                        ,RS.getString("description")
                        ,RS.getString("lien")
                        ,RS.getString("photo")
                        ,RS.getDouble("note")));

            }
            lsguide.stream().forEach((p) -> {
                System.out.println(p.toString());
            });
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
           
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
return lsguide;
    }
    
   /* public Guide get(String titre) {
         Guide a = new  Guide();
            try {
                String req = "SELECT * FROM `guide` WHERE `titre` = ?  ";
                PreparedStatement ste = con.prepareStatement(req);
                ste.setString(1, titre);
               ResultSet rs = ste.executeQuery();
                if (rs.next()) {
                    a.setId(rs.getInt("id"));
                    a.setCategorie(rs.getString("categorie"));
                      a.setDescription(rs.getString("description"));
                      a.setNote(rs.getDouble("note"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServiceGuide.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return a;
    }
    */
   
    public  List<Guide> searchByName(String titre){
         List<Guide> ls =new ArrayList<Guide>();
           try {
           
            PreparedStatement pt = con.prepareStatement("select * from guide where titre=?");
            pt.setString(1, titre);
            ResultSet rs = pt.executeQuery();
            while(rs.next()){

                Guide g = new Guide(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(8),rs.getDouble(7));
                ls.add(g);
            }

            for (Guide g : ls){
                System.out.println(g.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGuide.class.getName()).log(Level.SEVERE, null, ex);
        }
           return ls;
    }
public List<Guide> findlikeGuide(String s) {
    PreparedStatement   st=null ;
         ArrayList lstg=new ArrayList<>();
        lstg.clear();
        try {
     String      query = "select * from guide where titre LIKE '%" + s + "%' " ;
      st = con.prepareStatement(query);
          ResultSet rs = st.executeQuery();
            while(rs.next()){

                Guide g = new Guide(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getDouble(8));
                lstg.add(g);
            }
            lstg.stream().forEach((p) -> {
                System.out.println(p.toString());
            });
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
return lstg;
   
    }

public static int FindNombreJaimeById_Guide(int id_guide) throws SQLException {
    Statement st = null ;
        String req = "SELECT * from guide where id='" + id_guide + "'";
        ResultSet res = st.executeQuery(req);
        User com = null;
        int nbre = 0;
        while (res.next()) {

            nbre = res.getInt(7);
        }
        return nbre;
    }

public int FindValeurJaimeByIdUserAndIdGujet(int id_user, int id_guide) throws SQLException {
        String req = "SELECT * from likes WHERE id_user='" + id_user + "'AND id_guide='" + id_guide + "'";
       PreparedStatement ste=null;
        ResultSet res = ste.executeQuery(req);
        Likes jaime = null;
        int valeurJaime = 0;
        while (res.next()) {

            valeurJaime = res.getInt(3);
            System.out.println(valeurJaime);

        }
        return valeurJaime;
    }

     public List<Guide> TrierParDateCreation() throws SQLException {
        List<Guide> list = new ArrayList<>();
           PreparedStatement pt = con.prepareStatement("select * from guide ORDER BY date_creation desc LIMIT 2 ");
        ResultSet rs=pt.executeQuery();
        Guide com = null;
        while (rs.next()) {
            com = new   Guide(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(8),rs.getDouble(7));
            list.add(com);

        }
        System.out.println(list);
        return list;
    }
     
     
     public List<Guide> afficheGuide(String titre) throws SQLDataException {
  List<Guide> list=new ArrayList<Guide>();
           try {
               String req="SELECT * FROM `guide` where `titre`='"+titre+"'";
               Statement st;
                 st = con.createStatement();
                 ResultSet rs=st.executeQuery(req);
               
                while(rs.next())
                       {
                 Guide g = new Guide();
                g.setId(rs.getInt(1));
                g.setTitre(rs.getString(2));
                g.setDate_creation(rs.getString(3));
                g.setCategorie(rs.getString(4));
                g.setDescription(rs.getString(5));
                g.setLien(rs.getString(6));
                g.setPhoto(rs.getString(8));
                g.setNote(rs.getDouble(7));
               
                list.add(g);

                       }    
           } catch (SQLException ex) {
               Logger.getLogger(ServiceGuide.class.getName()).log(Level.SEVERE, null, ex);
           }
          return list;    }
      public Guide findGuideById(int id) {
        
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
             Logger.getLogger(ServiceGuide.class.getName()).log(Level.SEVERE, null, ex);
             return null;

         }
       }
    
}
