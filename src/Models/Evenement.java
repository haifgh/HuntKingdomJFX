/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;

/**
 *
 * @author Yosrio
 */
public class Evenement {
    private int id ;
    private int user_id;
    private String nom;
    private String description;
    private Date date_debut;
    private Date date_fin;
    private String validite;
    private int nbre_places;
    private String photo;
    private ListeParticipants inscriptions;
    private int nbrInscriptions;
    
    
    public Evenement(String nom, String description, Date date_debut,Date date_fin, String validite, int nbre_places, String photo) {
        this.nom = nom;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.validite = validite;
        this.nbre_places = nbre_places;
        this.photo = photo;
    }

    public Evenement() {
    }

    
    
    
    public Evenement(int id, String nom, String description, Date date_debut, Date date_fin, int nbre_places, String photo) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nbre_places = nbre_places;
        this.photo = photo;
    }
    
    

    public Evenement(int user_id, String nom, String description, Date date_debut, Date date_fin, String validite, int nbre_places, String photo) {
        this.user_id = user_id;
        this.nom = nom;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.validite = validite;
        this.nbre_places = nbre_places;
        this.photo = photo;
    }

    
    
    public Evenement(int id, int user_id, String nom, String description, Date date_debut, Date date_fin, String validite, int nbre_places, String photo) {
        this.id = id;
        this.user_id = user_id;
        this.nom = nom;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.validite = validite;
        this.nbre_places = nbre_places;
        this.photo = photo;
    }

    public Evenement(int id) {
       this.id = id;
    }

    public Evenement(String nom, String description, int nbre_places) {
        this.nom = nom;
        this.description = description;
        this.nbre_places = nbre_places;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", user_id=" + user_id + ", nom=" + nom + ", description=" + description + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", validite=" + validite + ", nbre_places=" + nbre_places + ", photo=" + photo + "\n" +'}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public String getValidite() {
        return validite;
    }

    public void setValidite(String validite) {
        this.validite = validite;
    }

    public int getNbre_places() {
        return nbre_places;
    }

    public void setNbre_places(int nbre_places) {
        this.nbre_places = nbre_places;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public ListeParticipants getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(ListeParticipants inscriptions) {
        this.inscriptions = inscriptions;
    }

    public int getNbrInscriptions() {
        return nbrInscriptions;
    }

    public void setNbrInscriptions(int nbrInscriptions) {
        this.nbrInscriptions = nbrInscriptions;
    }
    
    
    
    
    
    
     public String valid() {
        if (nom.equals("")) {
            return "titre";
        }
        if (description.equals("")) {
            return "description";
        }
        if (photo.equals("")) {
            return "image";
        }

        return null;
    }
     
     

}
