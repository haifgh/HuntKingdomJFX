/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author haifa
 */

import java.util.Date;
public class Guide {
    public static int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private int id ;
    private String titre;
    private String date_creation ;
    private String categorie;
    private String description;
    private String lien ;
    private String photo;
    private double note ;
   private int nbLikes=0;
    public Guide() {
    }
    
    public Guide(int id, String titre, String date_creation, String categorie, String description, String lien, String photo, double note) {
        this.id = id;
        this.titre = titre;
        this.date_creation = date_creation;
        this.categorie = categorie;
        this.description = description;
        this.lien = lien;
        this.photo = photo;
        this.note = note;
    }

    public Guide(int id, String titre, String date_creation, String description) {
        this.id = id;
        this.titre = titre;
        this.date_creation = date_creation;
        this.description = description;
    }

    public Guide(int id, String titre, String description, double note) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.note = note;
    }

    public Guide(String titre, String categorie, String description, String lien, String photo) {
        this.titre = titre;
        this.categorie = categorie;
        this.description = description;
        this.lien = lien;
        this.photo = photo;
    }
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Guide{" + "id=" + id + ", titre=" + titre + ", date_creation=" + date_creation + ", categorie=" + categorie + ", description=" + description + ", lien=" + lien + ", photo=" + photo + ", note=" + note + '}';
    }

    public int getNbLikes() {
        return nbLikes;
    }

    public void setNbLikes(int nbLikes) {
        this.nbLikes = nbLikes;
    }

    
    
}
