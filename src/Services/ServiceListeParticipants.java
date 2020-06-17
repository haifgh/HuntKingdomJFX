/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Models.Evenement;
import Models.ListeParticipants;
import Models.User;
import Utilities.Connexion;
import Utilities.UserSession;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Yosrio
 */
public class ServiceListeParticipants{
    private Connection con;
    private Statement ste;

    public ServiceListeParticipants() {
        con = Connexion.getInstance().getCnx();
    }
    
   

    Evenement e;
    

    Logger logger = Logger.getLogger(ServiceListeParticipants.class.getName());

    public void participer(ListeParticipants p) throws SQLException {
        String req = "INSERT INTO liste_participants(evenement_id) VALUES(?)";
        String req0 = "SELECT nbre_places FROM evenement WHERE id=?";
        String req1 = "UPDATE evenement SET nbre_places=? WHERE id=?";

        PreparedStatement pste = con.prepareStatement(req);

        pste.setInt(1, p.getEvenement().getId());

        pste.executeUpdate();

        PreparedStatement pste0 = con.prepareStatement(req0);
        pste0.setInt(1, p.getEvenement().getId());
        ResultSet r0 = pste0.executeQuery();
        r0.next();
        int a = r0.getInt("nbr_participants");
        int b = a + 1;

        PreparedStatement pste1 = con.prepareStatement(req1);
        pste1.setInt(1, b);
        pste1.setInt(2, p.getEvenement().getId());
        pste1.executeUpdate();
    }

    public void annulerparticiper(ListeParticipants p) throws SQLException {
        String req = "INSERT INTO liste_participants(evenement_id) VALUES(?)";
        String req0 = "SELECT nbre_places FROM evenement WHERE id=?";
        String req1 = "UPDATE evenement SET nbre_places=? WHERE id=?";

        PreparedStatement pste = con.prepareStatement(req);
        pste.setInt(1, p.getEvenement().getId());

        pste.executeUpdate();

        PreparedStatement pste0 = con.prepareStatement(req0);
        pste0.setInt(1, p.getEvenement().getId());
        ResultSet r0 = pste0.executeQuery();
        r0.next();
        int a = r0.getInt("nbr_participants");
        int b = a - 1;

        PreparedStatement pste1 = con.prepareStatement(req1);
        pste1.setInt(1, b);
        pste1.setInt(2, p.getEvenement().getId());
        pste1.executeUpdate();
    }

    public List<ListeParticipants> findAll() {
        List<ListeParticipants> inscriptions= new ArrayList<>();
        ListeParticipants pa = null;
        Evenement e;
        try {
            String query = "SELECT * from liste_participants where evenement_id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, pa.getId());
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                inscriptions.add(new ListeParticipants(
                        res.getInt("id"),
                        new Evenement(res.getInt("evenement_id")),
                        new User(res.getInt("user_id")),
                        res.getDate("date_creation")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return inscriptions;
    }

 
    public List<ListeParticipants> findInscriptionsByEvenementId(int idEvenement) {
        List<ListeParticipants> inscriptions = new ArrayList<>();
        try {
            String query = "SELECT * from liste_participants where evenement_id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, idEvenement);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                inscriptions.add(new ListeParticipants(
                        res.getInt("id"),
                        new Evenement(res.getInt("evenement_id")),
                        new User(res.getInt("user_id")),
                        res.getDate("date_participation")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return inscriptions;
    }

    public void saveInscription(ListeParticipants a) {
        try {
            ste = con.createStatement();
            String query = "INSERT INTO `pidev`.`liste_participants` (`id`,`evenement_id`, `user_id`, `date_participation`) values ('" + a.getId()+  "','" + a.getEvenement().getId()+  " ','" + UserSession.getInstance().getUser().getId()+ "','" + a.getDateParticipation()+ "')";
            //PreparedStatement statement = con.prepareStatement(query);
           /* statement.setInt(1,inscription.getId());
            statement.setInt(2, inscription.getEvenement().getId());
            statement.setInt(3, inscription.getUser().getId());
            statement.setDate(4, inscription.getDateParticipation());*/
            //statement.executeUpdate();
            ste.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void delete(ListeParticipants inscription) {
        try {
            String query = "DELETE  FROM liste_participants WHERE id=?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, inscription.getId());
            System.out.println(statement.toString());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    }

